// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores

import java.util.LinkedList;

public class HashTableChaining implements HashTable {

    private int size;
    private LinkedList<HashTableData>[] table;

    public HashTableChaining(int size) {
        // Lista encadeada que armazena os valores da tabela
        LinkedList<HashTableData>[] table = new LinkedList[size];

        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }

        this.table = table;
        this.size = size;
    }

    // Função hash
    public int hashFunction(int k) {
        return (int) Math.floor((0.61803 * k % 1) * this.size);
    }

    // Busca um valor na tabela hash pela chave
    @Override
    public String search(int key) {
        int hashKey = hashFunction(key);

        if (this.table[hashKey] != null){
            for (HashTableData data : this.table[hashKey]) {
                if (data.getKey() == key) {
                    return data.getValue();
                }
            }
        }
        return null;
    }

    // Insere uma chave e um valor na tabela
    @Override
    public String insert(int key, String value) {
        int hashKey = hashFunction(key);
        String data = search(key);

        if(data == null) {
            HashTableData Data =  new HashTableData(key, value);

            if(Data != null) {
               table[hashKey].add(Data);
               return "Chave-valor inserido";
            } else {
               return "Erro ao inserir chave-valor";
            }
        } else {
            for(HashTableData data1 : table[hashKey]) {
                if(data1.getKey() == key) {
                     data1.setValue(value);
                     return "Valor da chave atualizado";
                }
            }
        }
        return "Erro ao inserir chave valor";
    }

    // Remove um valor da tabela pela chave
    @Override
    public boolean remove(int key) {
        int hashKey = hashFunction(key);
        String data = search(key);

        if(data != null){
            for (HashTableData data1 : this.table[hashKey]) {
                if (data1.getKey() == key) {
                    table[hashKey].remove(data1);
                    return true;
                }
            }
        }

        return false;
    }

    // Exibe as informações da tabela hash
    public void print() {
        for (int i = 0; i < size; i++) {
            if(table[i].isEmpty()) {
                System.out.println(i + " - Null");
            } else {
                System.out.print(i);
                for (HashTableData data : this.table[i]) {
                    System.out.print(" - " + data.getKey() + ", " + data.getValue());
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
