package dataStructures;

/**
 *
 * @author lokci
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> {

    private Node first; // first node in the linked list

    private class Node { // linked-list node

        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            } // Search hit: update val.
        }
        first = new Node(key, val, first); // Search miss: add new node.
    }

    public Value get(Key key) { // Search for key, return associated value.
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val; // search hit
            }
        }
        return null; // search miss
    }

    public Key delete(Key key) {
        put(key, null);
        return key;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
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

    public Key ceiling(Key key) {
        int i = rank(key);
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
