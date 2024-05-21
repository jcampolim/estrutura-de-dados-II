// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores

import java.util.ArrayList;
import java.util.List;

public class HashTableOA implements HashTable {
    int key;
    String value;

    HashTableData table[];
    Boolean wasRemoved[];
    int size;

    public HashTableOA(int size) {
        table = new HashTableData[size];
        wasRemoved = new Boolean[size];

        for(int i = 0; i < size; i++) {
            wasRemoved[i] = false;
        }

        this.size = size;
    }

    // Função hash
    public int hashFunction(int k) {
        return k % size;
    }

    // Busca um valor na tabela hash pela chave
    @Override
    public String search(int key) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while(table[hashKey] != null || wasRemoved[hashKey]) {
            if(table[hashKey] != null) {
                if(table[hashKey].getKey() == key) {
                    return table[hashKey].getValue();
                }
            }

            hashKey = (hashKey + 1) % size;

            if (hashKey == originalHashKey) {
                return null;
            }
        }
        return null;
    }

    // Insere uma chave e um valor na tabela
    @Override
    public String insert(int key, String value) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while(table[hashKey] != null) {
            if(table[hashKey].getKey() == key) {
                table[hashKey].setValue(value);
                return "Valor da chave atualizado"; // Valor da chave atualizado
            }

            hashKey = (hashKey + 1) % size;

            if(hashKey == originalHashKey) {
                return "Erro ao inserir chave-valor"; // Erro ao inserir chave-valor
            }
        }

        table[hashKey] = new HashTableData(key, value);
        return "Chave-valor inserido"; // Chave-valor inserido
    }

    // Remove um valor da tabela pela chave
    @Override
    public boolean remove(int key) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while(table[hashKey] != null || wasRemoved[hashKey]) {
            if(table[hashKey] != null) {
                if(table[hashKey].getKey() == key) {
                    table[hashKey].setKey(0);
                    table[hashKey].setValue(null);
                    table[hashKey] = null;

                    wasRemoved[hashKey] = true;

                    return true;
                }
            }

            hashKey = (hashKey + 1) % size;

            if(hashKey == originalHashKey) {
                return false;
            }
        }



        return false;
    }

    // Exibe as informações da tabela hash
    public void print() {
        for(int i = 0; i < size; i++) {
            if(table[i] == null) {
                System.out.println(i + " - Null");
            } else {
                System.out.println(i + " - " + table[i].getKey() + ", " + table[i].getValue());
            }
        }
        System.out.println();
    }

}
