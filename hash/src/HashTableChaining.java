import java.util.LinkedList;

public class HashTableChaining implements HashTable {
    private int size;
    private LinkedList<HashTableData>[] table;
    public HashTableChaining(int size) {
        // um vetor de LinkedList que armazena o tipo HashTableData
        LinkedList<HashTableData>[] table = new LinkedList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        this.table = table;
        this.size = size;
    }

    public int hashFunction(int k) {
        return (int) Math.floor((0.61803 * k % 1) * this.size);
    }


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

    @Override
    public int insert(int key, String value) {
        int hashKey = hashFunction(key);
        String data = search(key);
        //Se não existir se adiciona
        if(data == null){
            HashTableData Data =  new HashTableData(key, value);
           if(Data != null){
               table[hashKey].add(Data);
               return 1; // Chave-valor inserido
           }
           else{
               return 0; // Erro ao inserir chave-valor
           }
        }else{ //Se existir modifique
            for (HashTableData data1 : table[hashKey]) {
                if (data1.getKey() == key) {
                     data1.setValue(value);
                     return 2; // Valor da chave atualizado
                }
            }
        }
        return 0; // Erro ao inserir chave-valor
    }

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
}
