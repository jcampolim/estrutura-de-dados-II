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
            // Se a linha for nula ou todos os caracteres já tiverem sido consumidos, então pode ir para a próxima linha
            if (line == null || pos >= line.length()) {

                // Se o StringBuilder não estiver vazio, cria um novo token
                if (sb.length() > 0) {
                    tokens.add(new Token(TokenType.STRING, sb.toString()));
                    sb.setLength(0);
                }

                // Se não for a primeira linha, adiciona uma quebra de linha
                if (line != null) {
                    tokens.add(new Token(TokenType.NEWLINE, "\n"));
                }

                // Se não tem mais linhas, então adiciona o fim de arquivo
                if (lineIndex >= contents.size()) {
                    tokens.add(new Token(TokenType.EOF, "\0"));
                    break;
                }

                // Lê a próxima linha e reinicia as variáveis de controle da linha
                line = contents.get(lineIndex++);

                pos = 0;
                currChar = '\0';
                isString = false;

                // Pula a linha se não tiver nada nela
                if (line.isBlank()) {
                    pos = line.length();
                    continue;
                }
            }

            if (!isString) {
                currChar = getNextChar();

                // Consome os espaços vazios
                if (Character.isWhitespace(currChar)) {
                    while (Character.isWhitespace(currChar)) {
                        currChar = getNextChar();
                    }

                    if (pos <= line.length() && !Character.isWhitespace(line.charAt(pos - 1))) {
                        --pos;
                    }

                // Reconhece um token de comentário
                } else if (currChar == '#') {
                    if (tokens.size() > 0 && tokens.get(tokens.size() - 1).getType() == TokenType.COMMENT) {
                        isString = true;
                        startStringWith(sb, currChar);
                    } else {
                        tokens.add(new Token(TokenType.COMMENT, "#"));
                    }

                // Reconhece uma string de escopo
                } else if(currChar == ')') {
                    if(tokens.size() > 0 && tokens.get(tokens.size() - 1).getType() == TokenType.COMMENT) {
                        isString = true;
                        startStringWith(sb, currChar);
                    } else {
                        tokens.add(new Token(TokenType.STRING, ")"));
                    }

                // Provavelmente a próxima sequência de caracteres é uma string
                } else if (currChar != '\0') {
                    isString = true;
                    startStringWith(sb, currChar);
                }

            } else {
                // Encontra uma string, parando no final da linha ou se encontrar '=' ou '('
                while (pos < line.length()) {
                    currChar = getNextChar();
                    sb.append(currChar);

                    if(currChar == '=' || currChar == '(') {
                        break;
                    }
                }

                if(currChar != '=' && currChar != '(') {
                    // Se a string encontrada for um comentário (se o token anterior for de comentário)
                    if(tokens.get(tokens.size() - 1).getType() == TokenType.COMMENT || tokens.get(tokens.size() - 2).getType() == TokenType.COMMENT) {
                        tokens.add(new Token(TokenType.STRING, sb.toString()));
                        sb.setLength(0);

                        isString = false;

                    // verifica se a string for um idenficador (o parenteses aberto precisa estar em outra linha)
                    } else {
                        line = contents.get(lineIndex++);

                        pos = 0;
                        currChar = '\0';
                        isString = false;

                        currChar = getNextChar();

                        // Consome espaço em branco
                        while(Character.isWhitespace(currChar)) {
                            currChar = getNextChar();
                        }

                        // É um identificador
                        if(currChar == '('){
                            tokens.add(new Token(TokenType.IDENTIFIER, sb.toString().trim()));
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
                        // É uma string qualquer
                        } else {
                            tokens.add(new Token(TokenType.STRING, sb.toString()));
                            sb.setLength(0);

                            isString = false;
                            pos = 0;
                            currChar = '\0';
                        }
                    }
                } else {
                    // Verifica se o identificador é de uma chave ou de um escopo
                    if(currChar == '=') {
                        // Se for uma chave, procura pelo valor e o associa a um token
                        tokens.add(new Token(TokenType.IDENTIFIER, sb.toString().substring(0, sb.length() - 1).trim()));
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
                        // Se for um escopo
                        tokens.add(new Token(TokenType.IDENTIFIER, sb.toString().substring(0, sb.length() - 1).trim()));
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

    // Avança para o próximo caractere
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
