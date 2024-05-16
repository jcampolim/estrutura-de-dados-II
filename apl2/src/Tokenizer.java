import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

//================================================================================
//        GRAMÁTICA
//================================================================================
//<scope>        ::= <identifier> (<blank> | <blank_line>)* "(" <blank_line>+ <data>* <blank> ")"
//<key>          ::= <identifier> <blank> "=" <blank> <value>
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

        while (true) {
            if (line == null || pos >= line.length()) {
                if (sb.length() > 0) {
                    tokens.add(new Token(TokenType.STRING, sb.toString()));
                    sb.setLength(0);
                }

                if (line != null) {
                    tokens.add(new Token(TokenType.NEWLINE, "\n"));
                }

                if (lineIndex >= contents.size()) {
                    tokens.add(new Token(TokenType.EOF, "\0"));
                    break;
                }

                line = contents.get(lineIndex++);

                pos = 0;
                currChar = '\0';
                isString = false;

                if (line.isBlank()) {
                    pos = line.length();
                    continue;
                }
            }

            if (!isString) {
                currChar = getNextChar();

                if (Character.isWhitespace(currChar)) {
                    while (Character.isWhitespace(currChar)) {
                        currChar = getNextChar();
                    }

                    if (pos <= line.length() && !Character.isWhitespace(line.charAt(pos - 1))) {
                        --pos;
                    }

                } else if (currChar == '#') {
                    if (tokens.size() > 0 && tokens.get(tokens.size() - 1).getType() == TokenType.COMMENT) {
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
                } else if (currChar != '\0') {
                    isString = true;
                    startStringWith(sb, currChar);
                }

            } else {
                // TODO: consumir os espaços em branco do final em caso de identificador
                while (pos < line.length()) {
                    currChar = getNextChar();
                    sb.append(currChar);

                    if(currChar == '=' || currChar == '(') {
                        break;
                    }
                }

                if(currChar != '=' && currChar != '(') {
                    if(tokens.get(tokens.size() - 1).getType() == TokenType.COMMENT || tokens.get(tokens.size() - 2).getType() == TokenType.COMMENT) {
                        tokens.add(new Token(TokenType.STRING, sb.toString()));
                        sb.setLength(0);

                        isString = false;
                    } else {
                        line = contents.get(lineIndex++);

                        pos = 0;
                        currChar = '\0';
                        isString = false;

                        currChar = getNextChar();

                        while(Character.isWhitespace(currChar)) {
                            currChar = getNextChar();
                        }

                        if(currChar == '('){
                            tokens.add(new Token(TokenType.IDENTIFIER, sb.toString()));
                            sb.setLength(0);

                            isString = false;
                            pos = 0;
                            currChar = '\0';

                            tokens.add(new Token(TokenType.STRING, "("));
                            currChar = getNextChar();

                            if(Character.isWhitespace(currChar)) {
                                while(Character.isWhitespace(currChar)) {
                                    currChar = getNextChar();
                                }
                            }
                        } else {
                            tokens.add(new Token(TokenType.STRING, sb.toString()));
                            sb.setLength(0);

                            isString = false;
                            pos = 0;
                            currChar = '\0';
                        }
                    }
                } else {
                    if(currChar == '=') {
                        tokens.add(new Token(TokenType.IDENTIFIER, sb.toString().substring(0, sb.length() - 1)));
                        sb.setLength(0);

                        tokens.add(new Token(TokenType.STRING, "="));

                        currChar = getNextChar();

                        while (pos < line.length()) {
                            currChar = getNextChar();
                            sb.append(currChar);
                        }

                        tokens.add(new Token(TokenType.VALUE, sb.toString()));
                        sb.setLength(0);
                        isString = false;                    } else {
                        tokens.add(new Token(TokenType.IDENTIFIER, sb.toString().substring(0, sb.length() - 1)));
                        sb.setLength(0);
                        tokens.add(new Token(TokenType.STRING, "("));

                        currChar = getNextChar();

                        if(Character.isWhitespace(currChar)) {
                            while(Character.isWhitespace(currChar)) {
                                currChar = getNextChar();
                            }
                        }
                    }
                }
            }
        }

        return tokens;
    }

    private char getNextChar() {
        if(pos >= line.length()) {
            return '\0';
        }
        return line.charAt(pos++);
    }

    private void startStringWith(StringBuilder sb, char c) {
        sb.setLength(0);
        sb.append(c);
    }
}
