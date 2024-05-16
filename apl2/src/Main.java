import BST.BST;
import AVL.AVL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> readFile() throws IOException {
        File file = new File("test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> fileList = new ArrayList<>();

        String line;
        while((line = br.readLine()) != null) {
            fileList.add(line);
        }

        return fileList;
    }
    public static void testParser(List<String> contents, BST bst, AVL avl) {
        Parser parser = new Parser();
        try {
            parser.run(contents, bst, avl);
            System.out.println("A gramática está correta");
        } catch(RuntimeException e) {
            System.out.println("\n**** ERRO! O conteúdo inserido não está bem formatado: ");
            System.out.println("> " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        BST bst = new BST();
        AVL avl = new AVL();
        testParser(readFile(), bst, avl);

    }
}