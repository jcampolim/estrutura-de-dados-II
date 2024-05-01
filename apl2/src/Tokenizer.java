import java.util.ArrayList;
import java.util.List;

//================================================================================
//        GRAMÁTICA
//================================================================================
//<data>         ::= ((<scope> | <key> | <comment>)* <blank_line>)*
//<scope>        ::= <identifier> <blank_line>* "(" <blank_line>+ <data>* <blank> ")"
//<key>          ::= <identifier> "=" <value>
//<identifier>   ::= <string>
//<value>        ::= <string>
//<comment>      ::= "#" <string>

//<string>       ::= <char>+
//<char>         ::= <basic_latin> | <latin_1_supp> | <whitespace>
//<basic_latin>  ::= [\u0020-\u007F]  ; Unicode Basic Latin
//<latin_1_supp> ::= [\u00A0-\u00FF]  ; Unicode Latin-1 Supplement

//<blank_line>   ::= <blank> <newline>
//<blank>        ::= <whitespace>*
//<whitespace>   ::= " " | "\t"
//<newline>      ::= "\n" | "\r" | "\r\n"

public class Tokenizer {
    private int pos;
    private String line = null;

    public Tokenizer() {
        pos = 0;
    }

    // A partir da lista de strings de entrada, contrói e retorna uma lista de tokens
    public List<Token> tokenize(List<String> contents) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int lineIndex = 0;
        char currChar = '\0';
        boolean isString = false;

        while(true) {
            // Pode avançar para a próxima linha? (verifica se é a primeira linha
            // ou se já chegou no final da linha atual)
            if(line == null || pos >= line.length()) {
                // Se o StringBuilder não estiver vazio (a linha não é nula), então um novo token é criado
                if(sb.length() > 0) {
                    tokens.add(new Token(TokenType.STRING, sb.toString()));
                    sb.setLength(0);
                }

                // Adiciona quebra de linha (se line == null então a primeira linha ainda não foi lida)
                if(line != null) {
                    tokens.add(new Token(TokenType.NEWLINE, "\n"));
                }

                // Adiciona token EOF se acabaram todas as linhas
                if(lineIndex >= contents.size()) {
                    tokens.add(new Token(TokenType.EOF, "\0"));
                    break;
                }

                // Lê a próxima linha
                line = contents.get(lineIndex++);

                // Reinicia as variáveis
                pos = 0;
                currChar = '\0';
                isString = false;

                // Se a linha for vazia ou espaços em branco, então a posição muda para o final da fila para
                // conseguir avançar para a próxima linha na próxima iteração
                if(line.isBlank()) {
                    pos = line.length();
                    continue;
                }
            }

            if(!isString) {
                currChar = getNextChar();

                // Reconhe um whitespace
                if(Character.isWhitespace((currChar))) {
                    // Considera ma sequencia de espaços em branco como um único espaço em branco
                    while(Character.isWhitespace((currChar))) {
                        currChar = getNextChar();
                    }
                    tokens.add(new Token(TokenType.WHITESPACE, " "));

                    // Volta para a ultima posição que não foi um espaço em branco
                    if(pos <= line.length() && !Character.isWhitespace(line.charAt(pos - 1))) {
                        --pos;
                    }
                } else if(currChar == '#') { // Reconhece token COMMENT
                    if(tokens.size() > 0 && tokens.get(tokens.size() - 1).getType() == TokenType.COMMENT) {
                        isString = true;
                        startStringWith(sb, currChar);
                    } else {
                        tokens.add(new Token(TokenType.COMMENT, "#"));
                    }
                } else if(currChar == ')') {
                    if(tokens.size() > 0 && tokens.get(tokens.size() - 1).getType() == TokenType.COMMENT) {
                        isString = true;
                        startStringWith(sb, currChar);
                    } else {
                        tokens.add(new Token(TokenType.STRING, ")"));
                    }
                } else if(currChar == '\0') {
                    isString = true;
                    startStringWith(sb, currChar);
                }
            } else { // Reconhece o token STRING
                // Forma uma string até a linha acabar ou até encontrar '=' ou '('
                while(pos < line.length()) {
                    sb.append(currChar);
                    currChar = getNextChar();

                    if(currChar == '=' || currChar == '(') {
                        break;
                    }
                }

                // Se o próximo char for '=', então a string anterior é um IDENTIFIER
                if(currChar == '=') {
                    tokens.add(new Token(TokenType.IDENTIFIER, sb.toString()));
                    sb.setLength(0);

                    tokens.add(new Token(TokenType.STRING, "="));

                    // Ecnontra a string que corresponde ao KEY
                    while(pos < line.length()) {
                        currChar = getNextChar();
                        sb.append(currChar);
                    }

                    tokens.add(new Token(TokenType.VALUE, sb.toString()));
                    sb.setLength(0);
                } else {
                    // Remove as linhas em branco ou espaços em branco
                    while(line.isBlank() || currChar == ' ' || currChar == '\t') {
                        pos = line.length();
                        currChar = getNextChar();
                    }

                    // Se o próximo char for '(', então a string anterior é um IDENTIFIER
                    if(currChar == '(') {
                        tokens.add(new Token(TokenType.IDENTIFIER, sb.toString()));
                        sb.setLength(0);

                        tokens.add(new Token(TokenType.STRING, "("));
                        // Se não, a string é apenas uma string
                    } else {
                        tokens.add(new Token(TokenType.STRING, sb.toString()));
                        sb.setLength(0);
                    }
                }
                isString = false;
            }
        }
        for(int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i).getType());
        }
        return tokens;
    }

    private char getNextChar() {
        if(pos >= line.length()) {
            return '\0';
        }
        return line.charAt((pos++));
    }

    private void startStringWith(StringBuilder sb, char c) {
        sb.setLength(0);
        sb.append(c);
    }
}
