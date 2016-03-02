package datatypes.custom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Test class to test Trie
 */
public class TrieTest {

    private Trie<Integer> rootNode;

    @Before
    public void init(){
        rootNode = new Trie<Integer>();
    }

    @Test
    public void testPutGet(){
        rootNode.put("key", 1);
        rootNode.put("keySpace", 3);
        rootNode.put("keyValue", 4);
        rootNode.put("secondKey", 2);
        Assert.assertEquals(new Integer(1), rootNode.get("key"));
        Assert.assertEquals(new Integer(3), rootNode.get("keySpace"));
        Assert.assertEquals(new Integer(4), rootNode.get("keyValue"));
        Assert.assertEquals(new Integer(2), rootNode.get("secondKey"));
        Assert.assertNull(rootNode.get("Secondkey"));
        Assert.assertEquals(4, rootNode.size());
    }

    @Test
    public void testHasKeyValue(){
        rootNode.put("uniqueKey", 30);
        Assert.assertTrue(rootNode.containsKey("uniqueKey"));
        Assert.assertFalse(rootNode.containsKey("noKey"));
        Assert.assertTrue(rootNode.containsValue(30));
        Assert.assertFalse(rootNode.containsValue(10));
    }

    @Test
    public void testClear(){
        rootNode.put("key", 1);
        rootNode.put("keySpace", 3);
        rootNode.put("keyValue", 4);
        rootNode.put("secondKey", 2);
        rootNode.clear();
        Assert.assertTrue(rootNode.isEmpty());
    }

    @Test
    public void testKeySet(){
        rootNode.put("k", 1);
        rootNode.put("ke", 3);
        rootNode.put("ka", 4);
        rootNode.put("kea", 4);
        Set keySet = rootNode.keySet();
        Assert.assertTrue(keySet.contains("ke"));
        Assert.assertEquals(4, keySet.size());
    }

    @Test
    public void testPutAll(){

        rootNode.put("key", 1);
        rootNode.put("keySpace", 3);
        rootNode.put("keyValue", 4);
        rootNode.put("secondKey", 2);


        Trie<Integer> test = new Trie<Integer>();
        test.putAll(rootNode);
        Assert.assertTrue(test.containsKey("keySpace"));
    }

    @Test
    public void testRemove(){
        rootNode.put("key", 1);
        rootNode.put("keySpace", 3);
        rootNode.put("keyValue", 4);
        rootNode.put("secondKey", 2);

        Assert.assertEquals(1,(long)rootNode.remove("key"));
        Assert.assertEquals(2,(long)rootNode.remove("secondKey"));
        Assert.assertEquals(3,(long)rootNode.remove("keySpace"));
        Assert.assertEquals(4,(long)rootNode.remove("keyValue"));
        Assert.assertTrue(rootNode.isEmpty());
    }
}
