import java.util.ArrayList;
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
                } else if (currChar == '+') {
                    tokens.add("+");
                    currChar = getNextChar();
                } else if (currChar == '*') {
                    tokens.add("*");
                    currChar = getNextChar();
                } else if (currChar == '\0') {
                    System.out.println("Chegou ao final!");
                    isTokenizing = false;
                } else {
                    System.out.println("Token não reconhecido: " + currChar);
                    isTokenizing = false;
                }
        }
        System.out.println("Encerrando....");
        return tokens;
    }

    public static void main(String[] args){
        Tokenizer t = new Tokenizer("25+     0.5          * 1 + A");
        List<String> tokens = t.tokenize();
        for(int i=0; i < tokens.size(); i++){
            System.out.println("Token[" + i + "]: " + tokens.get(i));
        }
    }
}
