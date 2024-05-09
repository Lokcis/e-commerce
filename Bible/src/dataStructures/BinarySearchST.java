package dataStructures;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @author lokci
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> implements Comparator<Key> {

    private Key[] keys;
    private Value[] vals;
    private int n;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave es nula");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("La clave es nula");
        }

        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        // key is already in table
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // insert new key-value pair
        if (n == keys.length) {
            resize(2 * keys.length);
        }

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("No se encontró la llave");
        }
        if (isEmpty()) {
            return;
        }

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length / 4) {
            resize(keys.length / 2);
        }
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("El argumento es nulo");
        }
        return get(key) != null;
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay claves en la tabla de símbolos");
        }
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay claves en la tabla de símbolos");
        }
        return keys[n - 1];
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("El valor " + k + " ingresado no se encuentra dentro del rango de valores.");
        }
        return keys[k];
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("El piso es nulo");
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            throw new NoSuchElementException("El valor es demasiado pequeño");
        } else {
            return keys[i - 1];
        }
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("El techo es nulo");
        }
        int i = rank(key);
        if (i == n) {
            throw new NoSuchElementException("El valor es demasiado grande");
        } else {
            return keys[i];
        }
    }

    private int rank(Key key, int lo, int hi) {
        if (key == null) {
            throw new IllegalArgumentException("La clave del rango es nula");
        }
        if (lo > hi) {
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

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave del rango es nula");
        }
        return rank(key, 0, n - 1);
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("La tabla de símbolos está vacía");
        }
        delete(min());
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("La tabla de símbolos está vacía");
        }
        delete(max());
    }

    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("El primer valor es nulo");
        }
        if (hi == null) {
            throw new IllegalArgumentException("El segundo valor es nulo");
        }

        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("El primer valor de las llaves es nulo");
        }
        if (hi == null) {
            throw new IllegalArgumentException("El segundo valor de las llaves es nulo");
        }

        Queue<Key> queue = new Queue<>();
        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }

    @Override
    public int compare(Key o1, Key o2) {
        return 1;
    }
}
