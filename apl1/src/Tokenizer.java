import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Tokenizer {
    private char[] input;
    private int index;

    public Tokenizer(String string){
        input = string.toCharArray();
        index = 0;
    }

    private char getNextChar(){
        if(index >= input.length) {return '\0';}
        return input[index++];
    }

    public List<String> tokenize(){
        // Um pouco de gambiarra mas tudo bem
        Character[] a = new Character[]{'+', '*', '(', ')', '-', '/'} ;
        List<Character> validTokens = Arrays.asList(a);

        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char currChar = getNextChar();

        boolean isTokenizing = true;
        while(isTokenizing) {
            while (Character.isWhitespace(currChar)) { // Ignora espaços em branco.
                sb.append(currChar);
                currChar = getNextChar();
            }
                if (Character.isDigit(currChar)) {
                    sb.setLength(0);
                    while (Character.isDigit(currChar) || currChar == '.') {
                        sb.append(currChar);
                        currChar = getNextChar();
                    }
                    tokens.add(sb.toString());
                } else if(validTokens.contains(currChar)){
                    tokens.add(Character.toString(currChar));
                    currChar = getNextChar();
                } else if(currChar == '\0'){
                    System.out.println("Chegou ao final da string.");
                    isTokenizing = false;
                }else{
                    System.out.println("Símbolo não reconhecido: " + currChar);
                    isTokenizing = false;
                    return null; // saida null é um sinal de Token invalido.
                }
        }
        return tokens;
    }

}
