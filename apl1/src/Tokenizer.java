// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// https://www.geeksforgeeks.org/overriding-in-java/
// Material disponibilizado pelo professor

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {
    private char[] input;
    private int index;

    public Tokenizer(String string) {
        input = string.toCharArray();
        index = 0;
    }

    private char getNextChar() {
        if(index >= input.length) {return '\0';}
        return input[index++];
    }

    public List<String> tokenize() {
        Character[] a = new Character[]{'+', '*', '(', ')', '-', '/'} ;
        List<Character> validTokens = Arrays.asList(a);

        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char currChar = getNextChar();

        boolean isTokenizing = true;
        while(isTokenizing) {
            // Ignora os espaços em branco
            while (Character.isWhitespace(currChar)) {
                sb.append(currChar);
                currChar = getNextChar();
            }

            // Verifica se o próximo char da string é válido
            if (Character.isDigit(currChar)) {
                sb.setLength(0);
                while (Character.isDigit(currChar) || currChar == '.') {
                    sb.append(currChar);
                    currChar = getNextChar();
                }
                tokens.add(sb.toString());
            } else if(validTokens.contains(currChar)) {
                tokens.add(Character.toString(currChar));
                currChar = getNextChar();
            } else if(currChar == '\0') {
                isTokenizing = false;
            }else {
                System.out.println("Símbolo não reconhecido: " + currChar);
                return null;
            }
        }
        return tokens;
    }

}
