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

        while (true) {
            // Pode avançar para a próxima linha?
            if (line == null || pos >= line.length()) {
                // Se o StringBuilder sb não está vazio neste ponto, significa que estávamos
                // montando uma string quando o cursor pos atingiu o final da linha.
                // Neste caso, adicionamos a string montada até o momento como um novo token.
                if (sb.length() > 0) {
                    tokens.add(new Token(TokenType.STRING, sb.toString()));
                    sb.setLength(0);
                }

                // Adiciona um token NEWLINE após a primeira linha, indicando quebra de linha.
                // (line == null neste momento significa que ainda vamos ler a primeira linha).
                if (line != null) {
                    tokens.add(new Token(TokenType.NEWLINE, "\n"));
                }

                // Acabaram as linhas?
                if (lineIndex >= contents.size()) {
                    // Adiciona um token EOF, indicando fim do conteúdo, e encerra o loop.
                    tokens.add(new Token(TokenType.EOF, "\0"));
                    break;
                }

                // Lê a próxima linha.
                line = contents.get(lineIndex++);

                // Reinicia variáveis a cada nova linha.
                pos = 0;
                currChar = '\0';
                isString = false;

                // Linha vazia ou somente espaços em branco?
                // Muda posição do cursor para o final da linha e encerra a iteração antecipadamente.
                // É importante mudar pos, para que na próxima iteração do loop o código entre nesta
                // condicional e avance para a próxima linha.
                if (line.isBlank()) {
                    pos = line.length();
                    continue;
                }
            }

            if (!isString) {
                currChar = getNextChar();

                if (Character.isWhitespace(currChar)) { // Reconhece um token WHITESPACE.
                    // Considera uma sequência de espaços em branco como um único espaço em branco.
                    while (Character.isWhitespace(currChar)) {
                        currChar = getNextChar();
                    }
                    tokens.add(new Token(TokenType.WHITESPACE, " "));

                    // Se passamos por uma sequência de espaços em branco, voltamos uma posição do cursor
                    // somente se o último caractere não for um espaço em branco, para que a instrução
                    // currChar = getNextChar(); na próxima iteração do loop obtenha o caractere correto.
                    // Isso deve ser feito porque no loop de sequência de espaços em branco acima,
                    // sempre avançamos para o próximo caractere.
                    if (pos <= line.length() && !Character.isWhitespace(line.charAt(pos - 1))) {
                        --pos;
                    }

                } else if (currChar == '#') { // Reconhece um token PRINT.
                    // Se o token anterior é um PRINT, então começa uma string (permite que uma string
                    // comece com o caractere '>').
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
                } else if (currChar != '\0') { // Provavelmente encontramos uma string.
                    // Adiciona o caractere atual como primeiro da string e ativa flag isString para que,
                    // a partir da próxima iteração, os próximos caracteres sejam adicionados à string.
                    isString = true;
                    startStringWith(sb, currChar);
                }

            } else { // Reconhece um token STRING.
                // Neste exemplo, a única condição para indicar que chegamos ao final de uma string é
                // ler todo o conteúdo da linha atual até o final da linha.
                while (pos < line.length()) {
                    currChar = getNextChar();
                    sb.append(currChar);

                    if(currChar == '=' || currChar == '(') {
                        break;
                    }
                }

                if(currChar != '=' && currChar != '(') {
                        while(line.isBlank() || Character.isWhitespace(currChar) || currChar == '\t' || currChar == '\n') {
                        currChar = getNextChar();
                        System.out.println(currChar);
                    }
                    System.out.println(currChar);
                    if(currChar == '(') {
                        tokens.add(new Token(TokenType.IDENTIFIER, sb.toString().substring(0, sb.length() - 1)));
                        sb.setLength(0);

                        tokens.add(new Token(TokenType.STRING, "("));
                    } else {
                        tokens.add(new Token(TokenType.STRING, sb.toString()));
                        sb.setLength(0);
                        isString = false;
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
                        isString = false;
                    } else {
                        tokens.add(new Token(TokenType.IDENTIFIER, sb.toString().substring(0, sb.length() - 1)));
                        sb.setLength(0);
                        tokens.add(new Token(TokenType.STRING, "("));
                    }
                }
            }
        }

        for(int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i));
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
