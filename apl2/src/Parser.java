import BST.*;
import AVL.AVL;
import AVL.ScopeAVL;
import AVL.KeyAVL;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Token> tokens;
    private Token currToken;
    private int index;

    public Parser() {
        tokens = null;
        currToken = null;
        index = -1;
    }

    public void run(List<String> contents, BST bst, AVL avl) {
        Tokenizer tokenizer = new Tokenizer();
        tokens = tokenizer.tokenize(contents);
        currToken = null;
        index = -1;

        parse(bst, avl);
    }

    private void parse(BST bst, AVL avl) {
        advance();
        data(bst, avl);
        if(currToken.getType() != TokenType.EOF) {
            throw new RuntimeException("Parser.parse(): Esperado o fim do conteúdo (EOF), mas encontrou " + currToken + ".");
        }
    }

    // <data> ::= ((<scope> | <key> | <comment>)* <blank_line>)*
    private void data(BST bst, AVL avl) {
        TokenType type = currToken.getType();
        List<String> path = new ArrayList<>();

        while(type == TokenType.COMMENT || type == TokenType.IDENTIFIER || type == TokenType.WHITESPACE) {
            if(type == TokenType.WHITESPACE) {
                consume(TokenType.WHITESPACE);
            }

            if(type == TokenType.COMMENT) {
                comment();
                consume(TokenType.NEWLINE);
            }

            if(type == TokenType.IDENTIFIER) {
                identifier(bst, avl, path);
                consume(TokenType.NEWLINE);
            }

            type = currToken.getType();

            if(type == TokenType.STRING) {
                throw new RuntimeException("Parser.data(): token do tipo STRING encontrado.");
            }
        }
    }

    private void comment() {
        consume(TokenType.COMMENT);

        while(currToken.getType() == TokenType.WHITESPACE) {
            consume(TokenType.WHITESPACE);
        }

        consume(TokenType.STRING);
    }

    public void identifier(BST bst, AVL avl, List<String> path) {
        String identifier = currToken.getValue();
        consume(TokenType.IDENTIFIER);

        if(currToken.getValue() == "=") {
            String value = key();

            KeyBST keyBST = new KeyBST(identifier, value);
            keyBST.setPath(new ArrayList<>(path));

            KeyAVL keyAVL = new KeyAVL(identifier, value);
            keyAVL.setPath(new ArrayList<>(path));

            bst.insert(keyBST);
            avl.insert(keyAVL);
        } else if(currToken.getValue() == "(") {
            ScopeBST scopeBST = new ScopeBST(identifier);
            scopeBST.setPath(new ArrayList<>(path));

            ScopeAVL scopeAVL = new ScopeAVL(identifier);
            scopeAVL.setPath(new ArrayList<>(path));

            bst.insert(scopeBST);
            avl.insert(scopeAVL);

            path.add(identifier);
            scope(bst, avl, path);
        }
    }

    public String key() {
        consume(TokenType.STRING);

        String value = currToken.getValue();
        consume(TokenType.VALUE);

        return value;
    }

    public void scope(BST bst, AVL avl, List<String> path) {
        consume(TokenType.STRING);

        boolean hasNewLine = false;
        while(true) {
            if(currToken.getType() == TokenType.NEWLINE) {
                hasNewLine = true;
                consume(TokenType.NEWLINE);
            }
            if(currToken.getType() == TokenType.IDENTIFIER) {
                identifier(bst, avl, path);
            }
            if(currToken.getType() == TokenType.COMMENT) {
                comment();
            }
            if(currToken.getType() == TokenType.STRING) {
                if(currToken.getValue() == ")") {
                    break;
                } else {
                    throw new RuntimeException("Parser.data(): token do tipo STRING encontrado.");
                }
            }

            if(currToken.getType() == TokenType.EOF) {
                throw new RuntimeException("Parser.scope(): ausencia de ')'.");
            }

            while(currToken.getType() == TokenType.WHITESPACE) {
                consume(TokenType.WHITESPACE);
            }
        }

        if(!hasNewLine) {
            throw new RuntimeException("Parser.scope(): Não há quebra de linha entre '(' e ')' do escopo.");
        }

        path.remove(path.size() - 1);
        consume(TokenType.STRING);
    }

    private void advance() {
        ++index;
        if (index >= tokens.size()) {
            throw new RuntimeException("Fim de conteúdo inesperado.");
        }
        currToken = tokens.get(index);
    }

    private void consume(TokenType expected) {
        if (currToken.getType() == expected) {
            advance();
        } else {
            throw new RuntimeException("Parser.consume(): Token incorreto. Esperado: " + expected + ". Obtido: " + currToken);
        }
    }
}
