import java.util.*;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private static class Node {
        boolean isKey;
        Map<Character, Node> map;

        Node(boolean isKey) {
            this.isKey = isKey;
            this.map = new HashMap<>();
        }
    }

    public MyTrieSet() {
        root = new Node(true);
    }

    @Override
    public void clear() {
        root = new Node(true);
    }

    @Override
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node endNode = endof(key);
        if (endNode == null) {
            return false;
        }
        return endNode.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    private Node endof(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        Node startNode = endof(prefix);
        List<String> keys = new ArrayList<>();
        if (startNode == null) {
            return keys;
        }
        collectAllKeys(prefix, startNode, keys);
        return keys;

    }

    private void collectAllKeys(String prefix, Node startNode, List<String> keys) {
        if (startNode.isKey) {
            keys.add(prefix);
        }
        for (char c : startNode.map.keySet()) {
            collectAllKeys(prefix + c, startNode.map.get(c), keys);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Input is null.");
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return key.substring(0, i);
            }
            curr = curr.map.get(c);
        }
        return key;
    }

    public static void main(String[] args) {
        MyTrieSet mts = new MyTrieSet();
        mts.add("why");
        mts.add("trie");
        mts.add("when");
//        System.out.println(mts.contains("trie"));
//        System.out.println(mts.contains("wh"));
//        System.out.println(mts.contains("a"));
        System.out.println(mts.keysWithPrefix("wh"));
//        System.out.println(mts.longestPrefixOf("w"));
    }
}
