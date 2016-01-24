package datatypes.custom;


import java.util.Map;
import java.util.Set;

/**
 * A custom implementation of Prefix Tree (known as Trie). It implements all general methods of a Key Pair store, similar to
 * a Map.
 */
public class Trie {

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
    private Trie[] subtree;
    /**
     * Value of this Node. For sake of simplicity, for now, this trie can only hold integer values.
     */
    private Integer value;

    private final int SUBTREE_LENGTH = 63;

    /**
     * Class constructor
     */
    public Trie(){
        this.count = 0;
        // @subtree could be a @HashMap, or an @ArrayList with initial size zero to avoid memory allocation. But when
        // we will call @get we either have to pass through a hashing algo or will have to iterate over @count number of
        // times. That would be a compromise of performance over memory. But for realtime applications I choose performance
        // and compromise over memory.
        this.subtree = new Trie[this.SUBTREE_LENGTH];
    }

    public int size() {
        return  count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public boolean containsKey(CharSequence key) {
        return false;
    }

    public boolean containsValue(Integer value) {
        return false;
    }

    public Integer get(CharSequence key) {
        return null;
    }

    public Integer put(CharSequence key, Integer value) {
        return null;
    }

    public Integer remove(CharSequence key) {
        return null;
    }

    public void putAll(Trie t) {
        Set<CharSequence> keySet = t.keySet();
        for(CharSequence key: keySet){
            this.put(key, t.get(key));
        }
    }

    public void clear() {
        this.subtree = new Trie[this.SUBTREE_LENGTH];
        this.count = 0;
    }

    public Set<CharSequence> keySet() {
        return null;
    }
}
