import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void testParser(final String[] testData) {
        List<String> contents = new ArrayList<String>();
        for(var s : testData) {
            contents.add(s);
        }

        Parser parser = new Parser();
        try {
            parser.run(contents);
            System.out.println("A gramática está correta");
        } catch(RuntimeException e) {
            System.out.println("\n**** ERRO! O conteúdo inserido não está bem formatado: ");
            System.out.println("> " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        final String[] test = {
                "# teste1",
                "teste2 = teste3",
                "teste 4 (",
                "teste 5 = teste 6",
                ")",
                "teste7",
                "(",

                ")"
        };

        testParser(test);
    }
}