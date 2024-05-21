// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores

public class Main {
    public static void main(String[] args) {

        System.out.println("-----------------------------------------------------");
        System.out.println("         HashTable de endereçamento aberto ");
        System.out.println("-----------------------------------------------------\n");

        HashTableOA teste1 = new HashTableOA(3);
  
        System.out.println(" > Busca por valor 0, que não existe: " + teste1.search(0));
        System.out.println(" > Remoção do valor 0, que não existe: " + teste1.remove(0));
        System.out.println();

        System.out.println(" > Inserir valor (0, 'Estrutura de dados I'): " + teste1.insert(0, "Estrutura de dados I"));
        teste1.print();

        System.out.println(" > Inserir valor (0, 'Estrutura de dados II'): " + teste1.insert(0, "Estrutura de dados II"));
        teste1.print();
        System.out.println();

        System.out.println(" > Preenchendo tabela: ");

        System.out.println("  > (1, 'Proj e Analise de Algoritmos II): " + teste1.insert(1, "Proj e Analise de Algoritmos II"));
        teste1.print();
        System.out.println("  > (2, 'Sistemas Operacionais): " + teste1.insert(2, "Sistemas Operacionais"));
        teste1.print();
        System.out.println("  > (3, 'Projeto de Software): " + teste1.insert(3, "Projeto de Software"));
        teste1.print();
        System.out.println("  > (4, 'Algoritmos Numericos): " + teste1.insert(3, "Algoritmos Numericos"));
        teste1.print();

        System.out.println("\n > Tentando remover chave 2 (existe): " + teste1.remove((2)));
        teste1.print();

        System.out.println(" > Tentando remover chave 3 (não existe): " + teste1.remove(3));
        teste1.print();

        System.out.println("\n > Buscando valores:");
        System.out.println("  > Buscando chave 0 (existe): " + teste1.search(0));
        System.out.println("  > Buscando chave 3 (não existe): " + teste1.search(3));
        System.out.println("  > Buscando chave 2 (foi removida): " + teste1.search(2));



        System.out.println("\n\n-----------------------------------------------------");
        System.out.println("                  HashTable ligada ");
        System.out.println("-----------------------------------------------------\n");

        HashTableChaining teste2 = new HashTableChaining(3);
        System.out.println(" > Busca por valor 0, que não existe: " + teste2.search(0));
        System.out.println(" > Remoção do valor 0, que não existe: " + teste2.remove(0));
        System.out.println();

        System.out.println(" > Inserir valor (0, 'Estrutura de dados I'): " + teste2.insert(0, "Estrutura de dados I"));
        teste2.print();

        System.out.println(" > Inserir valor (0, 'Estrutura de dados II'): " + teste2.insert(0, "Estrutura de dados II"));
        teste2.print();
        System.out.println();

        System.out.println(" > Preenchendo tabela: ");

        System.out.println("  > (1, 'Proj e Analise de Algoritmos II): " + teste2.insert(1, "Proj e Analise de Algoritmos II"));
        teste2.print();
        System.out.println("  > (2, 'Sistemas Operacionais): " + teste2.insert(2, "Sistemas Operacionais"));
        teste2.print();
        System.out.println("  > (3, 'Projeto de Software): " + teste2.insert(3, "Projeto de Software"));
        teste2.print();
        System.out.println("  > (4, 'Algoritmos Numericos): " + teste2.insert(3, "Algoritmos Numericos"));
        teste2.print();

        System.out.println("\n > Tentando remover chave 2 (existe): " + teste2.remove((2)));
        teste2.print();

        System.out.println(" > Tentando remover chave 9 (não existe): " + teste2.remove(9));
        teste2.print();

        System.out.println("\n > Buscando valores:");
        System.out.println("  > Buscando chave 0 (existe): " + teste2.search(0));
        System.out.println("  > Buscando chave 2 (foi removida): " + teste2.search(2));

        System.out.println("\nExemplo: tabela hash tamanho 3. Insiro chaves 0 e 3. Removo a chave 0. Busco a chave 3.\n");

        HashTableOA teste = new HashTableOA(3);
        teste.insert(0, "a");
        teste.insert(3, "b");

        teste.print();
        teste.remove(0);
        teste.print();

        System.out.println("buscando chave 3: " + teste.search(3));
    }
}
