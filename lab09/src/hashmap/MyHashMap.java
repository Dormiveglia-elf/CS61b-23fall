package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int N; // number of elements in the map
    private int M; // number of buckets
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        this.loadFactor = 0.75;
        this.M = 16;
        buckets = new Collection[M];
    }

    public MyHashMap(int initialCapacity) {
        this.M = initialCapacity;
        this.loadFactor = 0.75;
        buckets = new Collection[M];
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.M = initialCapacity;
        buckets = new Collection[M];
        this.loadFactor = loadFactor;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }


    /** Associates the specified value with the specified key in this map.
     *  If the map already contains the specified key, replaces the key's mapping
     *  with the value specified. */
    @Override
    public void put(K key, V value) {
        int bucketNum = Math.floorMod(key.hashCode(), M);
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    @Override
    public V get(K key) {
        for (int i = 0; i < M; i++) {
            for (Node j: buckets[i]) {
                if (j.key == key) {
                    return j.value;
                }
            }
        }
        return null;
    }

    /** Returns whether this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < M; i++) {
            for (Node j: buckets[i]) {
                if (j.key == key) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.N;
    }

    /** Removes every mapping from this map. */
    @Override
    public void clear() {
        for (int i = 0; i < M; i++) {
            buckets[i].clear();
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
