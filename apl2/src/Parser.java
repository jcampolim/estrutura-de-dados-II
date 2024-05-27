// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
// https://www.javatpoint.com/java-tokens
// https://www.devmedia.com.br/tokenizacao-com-scanner-em-java/26508
// SZWARCFITER, J.L.; MARKENZON, L. Estruturas de Dados e seus Algoritmos. 3ª. ed. Rio de Janeiro: LTC, 2010.

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

    public void run(List<String> contents, BST bst, AVL avl, List<String> path) {
        Tokenizer tokenizer = new Tokenizer();
        tokens = tokenizer.tokenize(contents);
        currToken = null;
        index = -1;

        parse(bst, avl, path);
    }

    private void parse(BST bst, AVL avl, List<String> path) {
        advance();
        data(bst, avl, path);
        if(currToken.getType() != TokenType.EOF) {
            throw new RuntimeException("Parser.parse(): Esperado o fim do conteúdo (EOF), mas encontrou " + currToken + ".");
        }
    }

    // <data> ::= ((<scope> | <key> | <comment>)* <blank_line>)*
    private void data(BST bst, AVL avl, List<String> path) {
        TokenType type = currToken.getType();

        // Consome 0+ regras do tipo <comment> e <identifier>
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

    // <comment>      ::= "#" <string>
    private void comment() {
        // Se for comentário, consome os tokens do comentário
        consume(TokenType.COMMENT);

        while(currToken.getType() == TokenType.WHITESPACE) {
            consume(TokenType.WHITESPACE);
        }

        consume(TokenType.STRING);
    }

    // <identifier>   ::= <string>
    public void identifier(BST bst, AVL avl, List<String> path) {
        String identifier = currToken.getValue();
        consume(TokenType.IDENTIFIER);

        // Verifica qual é o tipo do identifier (se é do <scope> ou do <key>) e adiciona nas árvores BST e AVL
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

    // <key>          ::= <identifier> <blank> "=" <blank> <value>
    public String key() {
        consume(TokenType.STRING);

        String value = currToken.getValue();
        consume(TokenType.VALUE);

        return value;
    }

    // <scope>        ::= <identifier> (<blank> | <blank_line>)* "(" <blank_line>+ <data>* <blank> ")"
    public void scope(BST bst, AVL avl, List<String> path) {
        consume(TokenType.STRING);

        // Verifica se possui o ')' com quebra de linha e valida os outros tokens que aparecem dentro do escopo
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

    // Avança para o próximo token
    private void advance() {
        ++index;
        if (index >= tokens.size()) {
            throw new RuntimeException("Fim de conteúdo inesperado.");
        }
        currToken = tokens.get(index);
    }

    // Avança para o próximo token, mas verifica se o próximo token é do tipo esperado
    private void consume(TokenType expected) {
        if (currToken.getType() == expected) {
            advance();
        } else {
            throw new RuntimeException("Parser.consume(): Token incorreto. Esperado: " + expected + ". Obtido: " + currToken);
        }
    }
}
