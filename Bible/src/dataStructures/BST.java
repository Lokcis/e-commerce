package dataStructures;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Implementación de un árbol binario de búsqueda (BST) genérico que permite
 * almacenar pares de claves y valores.
 *
 * @param <Key> El tipo de datos de las claves.
 * @param <Value> El tipo de datos de los valores asociados a las claves.
 */
public class BST<Key extends Comparable<Key>, Value> implements Comparator<Key> {

    private Node root;// Raíz del árbol

    /**
     * Compara dos claves.
     *
     * @param o1 La primera clave a comparar.
     * @param o2 La segunda clave a comparar.
     * @return 0 si las claves son iguales, un número negativo si o1 es menor
     * que o2, un número positivo si o1 es mayor que o2.
     */
    @Override
    public int compare(Key o1, Key o2) {
        return 1;
    }

    /**
     * Crea un árbol binario de búsqueda vacío.
     */
    public BST() {
    }

    /**
     * Clase interna que representa un nodo en el árbol binario de búsqueda.
     */
    private class Node {

        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * Verifica si el árbol está vacío.
     *
     * @return true si el árbol está vacío, false de lo contrario.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Obtiene el número de nodos en el árbol.
     *
     * @return El tamaño del árbol (número de nodos).
     */
    public int size() {
        return size(root);
    }

    /**
     * Obtiene el número de nodos en un subárbol con raíz en el nodo x.
     *
     * @param x El nodo raíz del subárbol.
     * @return El tamaño del subárbol.
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    /**
     * Verifica si el árbol contiene una clave específica.
     *
     * @param key La clave a buscar en el árbol.
     * @return true si el árbol contiene la clave, false de lo contrario.
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Obtiene el valor asociado a una clave específica en el árbol.
     *
     * @param key La clave cuyo valor se desea obtener.
     * @return El valor asociado a la clave, null si la clave no está presente.
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * Método auxiliar recursivo para obtener el valor asociado a una clave
     * específica en un subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @param key La clave cuyo valor se desea obtener.
     * @return El valor asociado a la clave en el subárbol, null si la clave no
     * está presente en el subárbol.
     */
    private Value get(Node x, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    /**
     * Agrega un par clave-valor al árbol. Si la clave ya está presente,
     * actualiza su valor.
     *
     * @param key La clave a agregar o actualizar.
     * @param val El valor asociado a la clave.
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        if (val == null) {
            delete(key);
        }
        root = put(root, key, val);
    }

    /**
     * Método auxiliar recursivo para agregar o actualizar un par clave-valor en
     * un subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @param key La clave a agregar o actualizar.
     * @param val El valor asociado a la clave.
     * @return El nodo raíz del subárbol actualizado.
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Elimina la clave más pequeña del árbol.
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow");
        }
        root = deleteMin(root);
    }

    /**
     * Método auxiliar recursivo para eliminar la clave más pequeña de un
     * subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @return El nodo raíz del subárbol actualizado después de la eliminación.
     */
    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Elimina la clave más grande del árbol.
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow");
        }
        root = deleteMax(root);
    }

    /**
     * Método auxiliar recursivo para eliminar la clave más grande de un
     * subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @return El nodo raíz del subárbol actualizado después de la eliminación.
     */
    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Elimina una clave y su valor asociado del árbol.
     *
     * @param key La clave a eliminar.
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls delete() with a null key");
        }
        root = delete(root, key);
    }

    /**
     * Método auxiliar recursivo para eliminar una clave y su valor asociado de
     * un subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @param key La clave a eliminar.
     * @return El nodo raíz del subárbol actualizado después de la eliminación.
     */
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);// Reemplaza el nodo por el sucesor más pequeño
            x.right = deleteMin(t.right);// Elimina el sucesor más pequeño del subárbol derecho
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Devuelve la clave más pequeña del árbol.
     *
     * @return La clave más pequeña del árbol.
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).key;
    }

    /**
     * Método auxiliar recursivo para encontrar la clave más pequeña en un
     * subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @return El nodo que contiene la clave más pequeña del subárbol.
     */
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    /**
     * Devuelve la clave más grande del árbol.
     *
     * @return La clave más grande del árbol.
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return max(root).key;
    }

    /**
     * Método auxiliar recursivo para encontrar la clave más grande en un
     * subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @return El nodo que contiene la clave más grande del subárbol.
     */
    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    /**
     * Devuelve la clave más grande menor o igual a la clave dada.
     *
     * @param key La clave de referencia.
     * @return La clave más grande menor o igual a la clave dada.
     * @throws IllegalArgumentException Si la clave dada es nula.
     * @throws NoSuchElementException Si el árbol está vacío.
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node x = floor(root, key);
        if (x == null) {
            throw new NoSuchElementException("argument to floor() is too small");
        } else {
            return x.key;
        }
    }

    /**
     * Método auxiliar recursivo para encontrar la clave más grande menor o
     * igual a la clave dada en un subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @param key La clave de referencia.
     * @return El nodo que contiene la clave más grande menor o igual a la clave
     * dada en el subárbol.
     */
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * Devuelve la clave más pequeña mayor o igual a la clave dada.
     *
     * @param key La clave de referencia.
     * @return La clave más pequeña mayor o igual a la clave dada.
     * @throws IllegalArgumentException Si la clave dada es nula.
     * @throws NoSuchElementException Si el árbol está vacío.
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            throw new NoSuchElementException("argument to ceiling() is too large");
        } else {
            return x.key;
        }
    }

    /**
     * Método auxiliar recursivo para encontrar la clave más pequeña mayor o
     * igual a la clave dada en un subárbol.
     *
     * @param x El nodo raíz del subárbol.
     * @param key La clave de referencia.
     * @return El nodo que contiene la clave más pequeña mayor o igual a la
     * clave dada en el subárbol.
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }
        return ceiling(x.right, key);
    }

    /**
     * Devuelve la clave en la posición del rango especificado.
     *
     * @param rank La posición en el rango de claves.
     * @return La clave en la posición especificada.
     * @throws IllegalArgumentException si el rango es inválido.
     */
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    /**
     * Devuelve la clave en la posición del rango especificado en el subárbol
     * dado.
     *
     * @param x El nodo raíz del subárbol.
     * @param rank La posición en el rango de claves.
     * @return La clave en la posición especificada.
     */
    private Key select(Node x, int rank) {
        if (x == null) {
            return null;
        }
        int leftSize = size(x.left);
        if (leftSize > rank) {
            return select(x.left, rank);
        } else if (leftSize < rank) {
            return select(x.right, rank - leftSize - 1);
        } else {
            return x.key;
        }
    }

    /**
     * Devuelve la posición de la clave dada en la tabla de símbolos.
     *
     * @param key La clave cuya posición se quiere conocer.
     * @return La posición de la clave dada.
     * @throws IllegalArgumentException si la clave es nula.
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key, root);
    }

    /**
     * Devuelve la posición de la clave dada en el subárbol con raíz en el nodo
     * dado.
     *
     * @param key La clave cuya posición se quiere conocer.
     * @param x El nodo raíz del subárbol.
     * @return La posición de la clave dada en el subárbol.
     */
    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    /**
     * Devuelve un iterable que recorre todas las claves en la tabla de
     * símbolos. El iterable devuelve las claves en orden ascendente. Si la
     * tabla está vacía, devuelve una cola vacía.
     *
     * @return Un iterable que recorre todas las claves en orden ascendente.
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    /**
     * Devuelve un iterable que recorre todas las claves en el rango [lo, hi].
     * Las claves se devuelven en orden ascendente. Si alguna de las claves es
     * nula, se lanza una excepción IllegalArgumentException.
     *
     * @param lo La clave mínima del rango.
     * @param hi La clave máxima del rango.
     * @return Un iterable que recorre todas las claves en el rango [lo, hi].
     * @throws IllegalArgumentException Si alguna de las claves es nula.
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * Llena la cola con las claves en el rango [lo, hi] del subárbol con raíz
     * en el nodo dado. Las claves se agregan a la cola en orden ascendente. Si
     * x es nulo, no se realiza ninguna operación.
     *
     * @param x El nodo raíz del subárbol.
     * @param queue La cola en la que se agregan las claves.
     * @param lo La clave mínima del rango.
     * @param hi La clave máxima del rango.
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    /**
     * Devuelve el número de claves en el rango [lo, hi] en la tabla de
     * símbolos. Si alguna de las claves es nula, se lanza una excepción
     * IllegalArgumentException.
     *
     * @param lo La clave mínima del rango.
     * @param hi La clave máxima del rango.
     * @return El número de claves en el rango [lo, hi].
     * @throws IllegalArgumentException Si alguna de las claves es nula.
     */
    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to size() is null");
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
}
