import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    public static final int DEFAULT_INITIAL_SIZE = 16;
    public static final double DEFAULT_LOAD_FACTOR = 0.75;
    int initialSize;
    double loadFactor;
    int size = 0;
    int threshold;
    BucketItem<K, V>[] hashTable;


    private static class BucketItem<K, V> {
        K key;
        V value;
        BucketItem<K, V> next;

        private BucketItem(K key, V value) {
            this(key, value, null);
        }

        private BucketItem(K key, V value, BucketItem<K, V> n) {
            this.key = key;
            this.value = value;
            this.next = n;
        }

        private boolean put(K key, V value) {
            return put(key, value, this);
        }

        private boolean put(K key,
                            V value,
                            BucketItem<K, V> kvBucketItem) {
            if (kvBucketItem.key == key) {
                kvBucketItem.value = value;
                return false;
            }
            if (kvBucketItem.next == null) {
                kvBucketItem.next = new BucketItem<>(key, value);
                return true;
            }
            return put(key, value, kvBucketItem.next);
        }

        private BucketItem<K, V> find(K key) {
            return find(key, this);
        }

        private BucketItem<K, V> find(K key, BucketItem<K, V> b) {
            if (b == null) {
                return null;
            }
            if (b.key.equals(key)) {
                return b;
            }
            return find(key, b.next);
        }

        private void add(BucketItem<K, V> x) {
            BucketItem<K, V> b = this;
            while (b.next != null) {
                b = b.next;
            }
            b.next = x;
        }

    }

    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.threshold = (int) (initialSize * loadFactor);
        hashTable = new BucketItem[initialSize];
    }

    @Override
    public void clear() {
        hashTable = new BucketItem[hashTable.length];
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int keyHash = hash(key);
        if (hashTable[keyHash] == null) {
            return false;
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int keyHash = hash(key);
        if (hashTable[keyHash] == null) {
            return null;
        }
        if (hashTable[keyHash] == key) {
            return hashTable[keyHash].value;
        }
        BucketItem<K, V> b = hashTable[keyHash].find(key);
        return b == null ? null : b.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            remove(key);
        }
        if (size + 1 > threshold) {
            resize();
        }
        int keyHash = hash(key);
        if (hashTable[keyHash] == null) {
            hashTable[keyHash] = new BucketItem<>(key, value);
            size += 1;
        } else {
            boolean isNew = hashTable[keyHash].put(key, value);
            if (isNew) {
                size += 1;
            }
        }
    }

    private void resize() {
        BucketItem<K, V>[] newTable = new BucketItem[initialSize * 2];
        threshold = (int) (newTable.length * loadFactor);
        for (BucketItem<K, V> b : hashTable) {
            if (b == null) {
                continue;
            }
            int newHash = hash(b.key, newTable.length);
            if (newTable[newHash] == null) {
                newTable[newHash] = b;
            } else {
                newTable[newHash].add(b);
            }
        }
    }

    private int hash(K key) {
        return hash(key, hashTable.length);
    }

    private int hash(K key, int capacity) {
        return (key.hashCode() & 0x7FFFFFFF) % capacity;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<>();
        for (BucketItem<K, V> b : hashTable) {
            while (b != null) {
                keySet.add(b.key);
                b = b.next;
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}