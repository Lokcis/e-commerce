package dataStructures;

/**
 *
 * @author lokci
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) { // See Algorithm 1.1 for standard array-resizing code.
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public void put(Key key, int lo, int hi, Value val) { // Search for key. Update value if found; grow table if new.
        int i = rank(key, lo, hi);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key, int lo, int hi) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key, lo, hi);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public Key delete(Key key, int lo, int hi) {
        put(key, lo, hi, null);
        return key;
    }

    public boolean contains(Key key, int lo, int hi) {
        return get(key, lo, hi) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return N;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key floor(Key key) {
        return key;
    }

    public Key ceiling(Key key, int lo, int hi) {
        int i = rank(key, lo, hi);
        return keys[i];
    }

    public int rank(Key key, int lo, int hi) {
        if (hi < lo) {
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public Key select(int k) {
        return keys[k];
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }
}
