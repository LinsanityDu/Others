// HashFuntion
In data structure Hash, hash function is used to convert a string(or any other type) into an integer smaller than hash size and bigger or equal to zero. The objective of designing a hash function is to "hash" the key as unreasonable as possible. A good hash function can avoid collision as less as possible. A widely used hash function algorithm is using a magic number 33, consider any string as a 33 based big integer like follow:

hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 

                              = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE

                              = 3595978 % HASH_SIZE

here HASH_SIZE is the capacity of the hash table (you can assume a hash table is like an array with index 0 ~ HASH_SIZE-1).

class Solution {
    /**
     * @param key: A String you should hash
     * @param HASH_SIZE: An integer
     * @return an integer
     */
    public int hashCode(char[] key,int HASH_SIZE) {
        // write your code here
        if (key == null || key.length == 0) {
            return 0;
        }
        long result = 0;
        for (int i = 0; i < key.length; i++) {
            result = result * 33 + (int)key[i];
            result %= HASH_SIZE;
        }
        return (int)result;
    }
}


package interviewquestions.other;

package interviewquestions.other;


// ThreadSafe？

public class MyHashMapEntry<K, V> {
    public K key;
    public V value;
    public MyHashMapEntry<K, V> next;
    
    public MyHashMapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public MyHashMapEntry(K key, V value, MyHashMapEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
/**
 * Implement a hash map
 * 
 */
public class MyHashMap<K, V> {
    private MyHashMapEntry<K, V>[] data;
    private static int DEFAULT_CAPACITY = 11;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int tableSize) {
        if (tableSize <= 0)
            throw new IllegalArgumentException("Error: invalid table size.");
        data = new MyHashMapEntry[tableSize];
        size = 0;
    }

    public void put(K key, V value) {
        int keyIndex = hash(key);
        MyHashMapEntry<K, V> entry = data[keyIndex];
        while (entry != null) {
            /**
             * If some of the key is empty, them must be deleted, put value into
             * it. Or if key is equal, them override value.
             */
            if ((entry.key == null && entry.value == null)
                    || (entry.key != null && entry.key.equals(key))) {
                entry.key = key;
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        /**
         * If not found place to insert, them put value into the beginning of
         * the chain
         */
        data[keyIndex] = new MyHashMapEntry<K, V>(key, value, data[keyIndex]);
        size++;
    }

    public V get(K key) {
        int keyIndex = hash(key);
        MyHashMapEntry<K, V> entry = data[keyIndex];
        while (entry != null) {
            if (entry.key.equals(key))
                break;
            entry = entry.next;
        }
        return entry == null ? null : entry.value;
    }

    public void remove(K key) {
        int keyIndex = hash(key);
        MyHashMapEntry<K, V> entry = data[keyIndex];
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.key = null;
                entry.value = null;
                size--;
                break;
            }
            entry = entry.next;
        }
    }

    public boolean containsKey(K key) {
        int keyIndex = hash(key);
        MyHashMapEntry<K, V> entry = data[keyIndex];
        while (entry != null) {
            if (entry.key != null && entry.key.equals(key))
                break;
            entry = entry.next;
        }
        return entry != null;
    }

    private int hash(K key) {
        if (key == null)
            return 0;
        else
            return Math.abs(key.hashCode() % data.length);
    }

    public int getSize() {
        return size;
    }

}