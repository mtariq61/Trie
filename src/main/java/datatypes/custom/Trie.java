package datatypes.custom;


import java.util.HashSet;
import java.util.Set;

/**
 * A custom implementation of Prefix Tree (known as Trie). It implements all general methods of a Key Pair store, similar to
 * a Map.
 */
public class Trie<T> {

    /**
     * Total count of elements in subtree
     */
    private int count;
    /**
     * Prefix Character of this node
     */
    private Character prefix;
    /**
     * Subtree of this Node
     */
    private Trie<T>[] subtree;
    /**
     * Value of this Node
     */
    private T value;

    private final int SUBTREE_LENGTH = 94; // Number of printable ascii characters

    /**
     * Class constructor
     */
    public Trie() {
        this.count = 0;
        // @subtree could be a @HashMap, or an @ArrayList with initial size zero to avoid memory allocation. But when
        // we will call @get we either have to pass through a hashing algo or will have to iterate over @count number of
        // times. That would be a compromise of performance over memory. But for realtime applications I choose performance
        // and compromise over memory.
        this.subtree = new Trie[this.SUBTREE_LENGTH];
    }

    @SuppressWarnings("unused")
    public T getValue(){
        return this.value;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public boolean containsKey(CharSequence key) {
        if (key == null || key.length() == 0) {
            return false;
        }
        if (subtree[this.getIndexFromASCII(key.charAt(0))] == null) {
            return false;
        }
        if (key.length() == 1) {
            return (subtree[this.getIndexFromASCII(key.charAt(0))].value != null);
        } else {
            return subtree[this.getIndexFromASCII(key.charAt(0))].containsKey(key.subSequence(1, key.length()));
        }
    }

    public boolean containsValue(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can not be null");
        }
        if (value.equals(this.value)) {
            return true;
        }
        for (Trie<T> entry : subtree) {
            if (entry != null) {
                if (entry.containsValue(value)) return true;
            }
        }
        return false;
    }

    public T get(CharSequence key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Key length should be at least one character");
        }
        int index = this.getIndexFromASCII(key.charAt(0));
        Character ch = key.charAt(0);
        if (subtree[index] == null) {
            return null;
        }
        if (key.length() == 1 && ch.equals(subtree[index].prefix)) { //Second condition is just a sanity check
            return subtree[index].value;
        }
        return subtree[index].get(key.subSequence(1, key.length()));
    }

    public T put(CharSequence key, T value) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Key length should be at least one character");
        }
        T toReturn;
        this.count++;
        int index = this.getIndexFromASCII(key.charAt(0));
        if (subtree[index] == null) {
            subtree[index] = new Trie<T>();
        }
        subtree[index].prefix = key.charAt(0);
        if (key.length() == 1) {
            toReturn = subtree[index].value;
            subtree[index].value = value;
        } else {
            toReturn = subtree[index].put(key.subSequence(1, key.length()), value);
        }

        return toReturn;
    }

    public T remove(CharSequence key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Key length should be at least one character");
        }
        T toReturn = null;
        this.count--;
        int index = this.getIndexFromASCII(key.charAt(0));
        if (subtree[index] != null) {
            if(key.length() == 1){
                toReturn = subtree[index].value;
                if(subtree[index].isEmpty()){
                    subtree[index] = null;
                } else {
                    subtree[index].value = null;
                }
            } else {
                toReturn = subtree[index].remove(key.subSequence(1, key.length()));
            }
        }
        return toReturn;
    }

    public void putAll(Trie<T> t) {
        Set<CharSequence> keySet = t.keySet();
        for (CharSequence key : keySet) {
            this.put(key, t.get(key));
        }
    }

    public void clear() {
        this.subtree = new Trie[this.SUBTREE_LENGTH];
        this.count = 0;
    }

    public Set<CharSequence> keySet() {
        Set<CharSequence> keySet = new HashSet<CharSequence>();
        for (Trie<T> entry : subtree) {
            Set<CharSequence> entryKeySet;
            if (entry != null) {
                entryKeySet = entry.keySet();
                if (this.prefix != null) {
                    for (CharSequence key : entryKeySet) {
                        keySet.add(this.prefix + key.toString());
                    }
                } else {
                    keySet.addAll(entryKeySet);
                }
            }
        }
        if(this.value != null){
            keySet.add(this.prefix.toString());
        }
        return keySet;
    }

    private int getIndexFromASCII(int ascii) {
        return ascii - 33;
    }
}
