package dataStructures;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Una tabla de símbolos implementada utilizando búsqueda binaria. Esta clase
 * proporciona operaciones para almacenar y recuperar pares clave-valor, así
 * como otras operaciones auxiliares.
 *
 * @author espin
 * @param <Key> El tipo de las claves almacenadas en la tabla.
 * @param <Value> El tipo de los valores asociados a las claves.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> implements Comparator<Key> {

    private Key[] keys;
    private Value[] vals;
    private int n;

    /**
     * Crea una nueva tabla de símbolos con la capacidad especificada.
     *
     * @param capacity La capacidad inicial de la tabla de símbolos.
     */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    /**
     * Aumenta la capacidad de la tabla de símbolos si es necesario.
     *
     * @param capacity La nueva capacidad deseada.
     */
    private void resize(int capacity) {
        if (capacity < n) {
            throw new IllegalArgumentException("La capacidad no puede ser menor que el número de elementos existentes");
        }
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    /**
     * Devuelve el número de elementos en la tabla de símbolos.
     *
     * @return El número de elementos.
     */
    public int size() {
        return n;
    }

    /**
     * Devuelve `true` si la tabla de símbolos está vacía, `false` de lo
     * contrario.
     *
     * @return `true` si la tabla de símbolos está vacía, `false` de lo
     * contrario.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Obtiene el valor asociado a una clave dada.
     *
     * @param key La clave cuyo valor se desea obtener.
     * @return El valor asociado a la clave, o `null` si la clave no está
     * presente.
     * @throws IllegalArgumentException Si la clave es `null`.
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave es nula");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && compare(keys[i], key) == 0) {
            return vals[i];
        }
        return null;
    }

    /**
     * Inserta una clave y su valor asociado en la tabla de símbolos.
     *
     * @param key La clave a insertar.
     * @param val El valor asociado a la clave.
     * @throws IllegalArgumentException Si la clave es `null`.
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("La clave es nula");
        }

        if (val == null || (Integer) val <= 0) {
            delete(key);
            return;
        }

        int i = rank(key);

        // key is already in table
        if (i < n && compare(keys[i], key) == 0) {
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

    /**
     * Elimina una clave y su valor asociado de la tabla de símbolos.
     *
     * @param key La clave a eliminar.
     * @return `null` si la clave se eliminó con éxito, de lo contrario la
     * clave.
     * @throws IllegalArgumentException Si la clave es `null`.
     * @throws NoSuchElementException Si la tabla de símbolos está vacía o la
     * clave no está presente.
     */
    public Key delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("No se encontró la llave");
        }
        if (isEmpty()) {
            throw new IllegalArgumentException("El arreglo está vacío");
        }

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || compare(keys[i], key) != 0) {
            return key;
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
        return null;
    }

    /**
     * Verifica si la tabla de símbolos contiene una clave dada.
     *
     * @param key La clave a buscar.
     * @return `true` si la clave está presente, `false` de lo contrario.
     * @throws IllegalArgumentException Si la clave es `null`.
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("El argumento es nulo");
        }
        return get(key) != null;
    }

    /**
     * Devuelve la clave más pequeña en la tabla de símbolos.
     *
     * @return La clave más pequeña.
     * @throws NoSuchElementException Si la tabla de símbolos está vacía.
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay claves en la tabla de símbolos");
        }
        return keys[0];
    }

    /**
     * Devuelve la clave más grande en la tabla de símbolos.
     *
     * @return La clave más grande.
     * @throws NoSuchElementException Si la tabla de símbolos está vacía.
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay claves en la tabla de símbolos");
        }
        return keys[n - 1];
    }

    /**
     * Devuelve la k-ésima clave en orden ascendente.
     *
     * @param k El índice de la clave deseada.
     * @return La k-ésima clave.
     * @throws IllegalArgumentException Si el índice está fuera del rango
     * válido.
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("El valor " + k + " ingresado no se encuentra dentro del rango de valores.");
        }
        return keys[k];
    }

    /**
     * Devuelve la clave más grande menor o igual a la clave dada.
     *
     * @param key La clave de referencia.
     * @return La clave más grande menor o igual a la clave dada.
     * @throws IllegalArgumentException Si la clave es `null`.
     * @throws NoSuchElementException Si no hay una clave menor o igual a la
     * clave dada.
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("El piso es nulo");
        }
        int i = rank(key);
        if (i < n && compare(key, keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            throw new NoSuchElementException("El valor es demasiado pequeño");
        } else {
            return keys[i - 1];
        }
    }

    /**
     * Devuelve la clave más pequeña mayor o igual a la clave dada.
     *
     * @param key La clave de referencia.
     * @return La clave más pequeña mayor o igual a la clave dada.
     * @throws IllegalArgumentException Si la clave es `null`.
     * @throws NoSuchElementException Si no hay una clave mayor o igual a la
     * clave dada.
     */
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

    /**
     * Devuelve la posición de una clave en un rango específico de la tabla de
     * símbolos.
     *
     * @param key La clave a buscar.
     * @param lo El índice inferior del rango de búsqueda.
     * @param hi El índice superior del rango de búsqueda.
     * @return La posición de la clave en el rango o la posición donde debería
     * estar.
     * @throws IllegalArgumentException Si la clave es `null`.
     */
    private int rank(Key key, int lo, int hi) {
        if (key == null) {
            throw new IllegalArgumentException("La clave del rango es nula");
        }
        if (lo > hi) {
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = compare(key, keys[mid]);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    /**
     * Devuelve la posición de una clave en la tabla de símbolos. Si la clave no
     * está presente, devuelve la posición donde debería estar.
     *
     * @param key La clave a buscar.
     * @return La posición de la clave en la tabla o la posición donde debería
     * estar.
     * @throws IllegalArgumentException Si la clave es `null`.
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave del rango es nula");
        }
        return rank(key, 0, n - 1);
    }

    /**
     * Elimina la clave más pequeña de la tabla de símbolos. Lanza una excepción
     * si la tabla de símbolos está vacía.
     *
     * @throws NoSuchElementException Si la tabla de símbolos está vacía.
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("La tabla de símbolos está vacía");
        }
        delete(min());
    }

    /**
     * Elimina la clave más grande de la tabla de símbolos. Lanza una excepción
     * si la tabla de símbolos está vacía.
     *
     * @throws NoSuchElementException Si la tabla de símbolos está vacía.
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("La tabla de símbolos está vacía");
        }
        delete(max());
    }

    /**
     * Devuelve el número de claves en un rango dado. El rango incluye las
     * claves desde `lo` hasta `hi`, ambos inclusive.
     *
     * @param lo La clave más pequeña del rango.
     * @param hi La clave más grande del rango.
     * @return El número de claves en el rango dado.
     * @throws IllegalArgumentException Si alguna de las claves es `null` o `lo`
     * es mayor que `hi`.
     */
    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("El primer valor es nulo");
        }
        if (hi == null) {
            throw new IllegalArgumentException("El segundo valor es nulo");
        }

        if (compare(lo, hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * Devuelve un iterable de todas las claves en la tabla de símbolos. Las
     * claves están en orden ascendente.
     *
     * @return Un iterable de todas las claves en orden ascendente.
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Devuelve un iterable de claves en el rango dado. Las claves están en
     * orden ascendente.
     *
     * @param lo La clave más pequeña del rango.
     * @param hi La clave más grande del rango.
     * @return Un iterable de claves en el rango dado en orden ascendente.
     * @throws IllegalArgumentException Si alguna de las claves es `null` o `lo`
     * es mayor que `hi`.
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("El primer valor de las llaves es nulo");
        }
        if (hi == null) {
            throw new IllegalArgumentException("El segundo valor de las llaves es nulo");
        }

        Queue<Key> queue = new Queue<>();
        if (compare(lo, hi) > 0) {
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

    /**
     * Comparador utilizado para ordenar las claves en orden descendente.
     *
     * @param o1 La primera clave a comparar.
     * @param o2 La segunda clave a comparar.
     * @return Un valor negativo, cero o positivo si la primera clave es menor,
     * igual o mayor que la segunda clave respectivamente.
     */
    @Override
    public int compare(Key o1, Key o2) {
        return o2.compareTo(o1);
    }
}
