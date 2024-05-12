public class Main {
    public static void main(String[] args) {
        HashTableOA teste1 = new HashTableOA(3);




        System.out.println("Testes para HashTable ligada: ");

        HashTableChaining teste2 = new HashTableChaining(3);

        System.out.println("Busca por valor 0, que não existe: " + teste2.search(0));

        System.out.println("Remoção do valor 0, que não existe: " + teste2.remove(0));

        System.out.println("Inserir valor (0,0): " + teste2.insert(0, "0"));

        System.out.println("Inserir valor (0,1): " + teste2.insert(0, "1"));

        System.out.println("Enchendo com os valores (1,1), (2,2), (3,3) e (4,4):");
        teste2.insert(1, "1");
        teste2.insert(2, "2");
        teste2.insert(3, "3");
        teste2.insert(4, "4");


        System.out.println("Busca por chave 0: " + teste2.search(0));
        System.out.println("Busca por chave 1: " + teste2.search(1));
        System.out.println("Busca por chave 2: " + teste2.search(2));
        System.out.println("Busca por chave 3: " + teste2.search(3));
        System.out.println("Busca por chave 4: " + teste2.search(4));









    }
}