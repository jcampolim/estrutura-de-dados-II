public class HashTableOA implements HashTable {
    int key;
    String value;

    HashTableData table[];
    int size;

    public HashTableOA(int size) {
        table = new HashTableData[size];
        this.size = size;
    }

    public int hashFunction(int k) {
        return k % size;
    }

    @Override
    public String search(int key) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while(table[hashKey] != null) {
            if(table[hashKey].getKey() == key) {
                return table[hashKey].getValue();
            }

            hashKey = (hashKey + 1) % size;

            if (hashKey == originalHashKey) {
                return null;
            }
        }
        return null;
    }

    @Override
    public int insert(int key, String value) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while(table[hashKey] != null) {
            if(table[hashKey].getKey() == key) {
                table[hashKey].setValue(value);
                return 2; // Valor da chave atualizado
            }

            hashKey = (hashKey + 1) % size;

            if(hashKey == originalHashKey) {
                return 0; // Erro ao inserir chave-valor
            }
        }

        table[hashKey] = new HashTableData(key, value);
        return 1; // Chave-valor inserido
    }

    @Override
    public boolean remove(int key) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while(table[hashKey] != null) {
            if(table[hashKey].getKey() == key) {
                table[hashKey].setKey(0);
                table[hashKey].setValue(null);
                table[hashKey] = null;

                return true;
            }

            hashKey = (hashKey + 1) % size;

            if(hashKey == originalHashKey) {
                return false;
            }
        }

        for(int i = 0; i < size; i++) {
            int aux = (hashKey + i) % size;
            if (table[aux] != null && table[aux].getKey() == key) {
                table[hashKey].setKey(0);
                table[hashKey].setValue(null);
                table[hashKey] = null;

                return true;
            }
        }

        return false;
    }

    public void print() {
        for(int i = 0; i < size; i++) {
            if(table[i] == null) {
                System.out.println("Null");
            } else {
                System.out.println(table[i].getKey() + " " + table[i].getValue());
            }
        }
    }

}
