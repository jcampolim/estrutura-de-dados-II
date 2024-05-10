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
            if(type == TokenType.WHITESPACE) {
                consume(TokenType.WHITESPACE);
            }

            if(type == TokenType.COMMENT) {
                comment();
                consume(TokenType.NEWLINE);
            }

            if(type == TokenType.IDENTIFIER) {
                identifier();
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

    public void identifier() {
        consume(TokenType.IDENTIFIER);

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
        while(true) {
            if(currToken.getType() == TokenType.NEWLINE) {
                hasNewLine = true;
                consume(TokenType.NEWLINE);
            }
            if(currToken.getType() == TokenType.IDENTIFIER) {
                identifier();
            }
            if(currToken.getType() == TokenType.COMMENT) {
                comment();
            }
            if(currToken.getType() == TokenType.STRING) {
                if(currToken.getValue() == ")") {
                    break;
                }
            }

            while(currToken.getType() == TokenType.WHITESPACE) {
                consume(TokenType.WHITESPACE);
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
