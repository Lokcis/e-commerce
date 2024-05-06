package dataStructures;

/**
 *
 * @author lokci
 * @param <Key>
 * @param <Value>
 */
import java.util.*;

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
        Node prev = null;
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                if (prev == null) {
                    first = x.next;
                } else {
                    prev.next = x.next;
                }
                return key;
            }
            prev = x;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int size = 0;
        for (Node x = first; x != null; x = x.next) {
            size++;
        }
        return size;
    }

    public Iterable<Key> keys() {
        LinkedList<Key> queue = new LinkedList<Key>();
        for (Node x = first; x != null; x = x.next) {
            queue.add(x.key);
        }
        return queue;
    }
}
