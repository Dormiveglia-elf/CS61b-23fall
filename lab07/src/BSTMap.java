import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K k, V v) {
            key = k;
            value = v;
            left = right = null;
        }
    }

    private int size = 0;
    Node root;

    BSTMap() {
        root = null;
    }

    private void deleteKey(K key) {
        root = deleteRecursive(root, key);
        size -= 1;
    }

    private Node deleteRecursive(Node root, K key) {
        if (root == null) {
            return root;
        }
        if (key.compareTo(root.key) < 0) {
            root.left = deleteRecursive(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = deleteRecursive(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);
            root.right = deleteRecursive(root.right, root.key);
        }
        return root;
    }

    private K minValue(Node root) {
        K minval = root.key;
        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    private void insert(K key, V val) {
        root = insertRecursive(root, key, val);
        size += 1;
    }

    private Node insertRecursive(Node root, K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            return root;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = insertRecursive(root.left, key, val);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRecursive(root.right, key, val);
        }
        return root;
    }

    private boolean update(K key, V val) {
        Node temp = root;
        temp = updateRecursive(temp, key, val);
        if (temp != null) {
            return true;
        } else {
            return false;
        }
    }

    private Node updateRecursive(Node temp, K key, V val) {
        if (temp == null) {
            return temp;
        }
        if (temp.key.compareTo(key) == 0) {
            temp.value = val;
            return temp;
        } else if (temp.key.compareTo(key) > 0) {
            return updateRecursive(temp.left, key, val);
        } else {
            return updateRecursive(temp.right, key, val);
        }
    }

    private V getValue(K key) {
        Node temp = root;
        temp = getValueRecursive(temp, key);
        if (temp != null) {
            return temp.value;
        } else {
            return null;
        }
    }

    private Node getValueRecursive(Node temp, K key) {
        if (temp == null || temp.key.compareTo(key) == 0) {
            return temp;
        }

        if (temp.key.compareTo(key) < 0) {
            return getValueRecursive(temp.right, key);
        } else {
            return getValueRecursive(temp.left, key);
        }
    }

    private boolean search(K key) {
        Node temp = root;
        temp = searchRecursive(temp, key);
        if (temp != null) {
            return true;
        } else {
            return false;
        }
    }

    private Node searchRecursive(Node temp, K key) {
        if (temp == null) {
            return temp;
        }
        if (temp.key.compareTo(key) == 0) {
            return temp;
        } else if (temp.key.compareTo(key) > 0) {
            return searchRecursive(temp.left, key);
        } else {
            return searchRecursive(temp.right, key);
        }
    }

    /** Associates the specified value with the specified key in this map.
     *  If the map already contains the specified key, replaces the key's mapping
     *  with the value specified. */
    @Override
    public void put(K key, V value) {
        boolean isUpdated;
        isUpdated = update(key, value);
        if (!isUpdated) {
            insert(key, value);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    @Override
    public V get(K key) {
        return getValue(key);
    }

    @Override
    public boolean containsKey(K key) {
        return search(key);
    }

    @Override
    public int size() {
        return size;
    }

    /** Removes every mapping from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
