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

    public void run(List<String> contents) {
        Tokenizer tokenizer = new Tokenizer();
        tokens = tokenizer.tokenize(contents);
        currToken = null;
        index = -1;

        parse();
    }

    private void parse() {
        advance();
        data();
        if(currToken.getType() != TokenType.EOF) {
            throw new RuntimeException("Parser.parse(): Esperado o fim do conteúdo (EOF), mas encontrou " + currToken + ".");
        }
    }

    // <data> ::= ((<scope> | <key> | <comment>)* <blank_line>)*
    private void data() {
        TokenType type = currToken.getType();
        while(type == TokenType.COMMENT || type == TokenType.IDENTIFIER || type == TokenType.WHITESPACE) {
            if(type == TokenType.COMMENT) {
                comment();
            } else if(type == TokenType.IDENTIFIER) {
                identifier();
            }

            consume(TokenType.NEWLINE);
            type = currToken.getType();
        }
    }

    private void comment() {
        consume(TokenType.COMMENT);

        while(currToken.getType() == TokenType.WHITESPACE) {
            consume(TokenType.WHITESPACE);
        }
        consume(TokenType.STRING);
    }

    public void identifier() {
        Token identifier = currToken;
        TokenType type = currToken.getType();

        consume(TokenType.IDENTIFIER);
        type = currToken.getType();

        if(currToken.getValue() == "=") {
            key();
        } else if(currToken.getValue() == "(") {
            scope();
        }
    }

    public void key() {
        consume(TokenType.STRING);
        consume(TokenType.VALUE);
    }

    public void scope() {
        consume(TokenType.STRING);

        boolean hasNewLine = false;
        while(currToken.getValue() != ")") {
            if(currToken.getType() == TokenType.WHITESPACE || currToken.getType() == TokenType.NEWLINE) {
                hasNewLine = true;
                if(currToken.getType() == TokenType.WHITESPACE) {
                    consume(TokenType.WHITESPACE);
                } else {
                    consume(TokenType.NEWLINE);
                }
            }
            if(currToken.getType() == TokenType.IDENTIFIER) {
                identifier();
                consume(TokenType.NEWLINE);
            }
        }

        if(!hasNewLine) {
            throw new RuntimeException("Parser.scope(): Não há quebra de linha entre '(' e ')' do escopo.");
        }
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
