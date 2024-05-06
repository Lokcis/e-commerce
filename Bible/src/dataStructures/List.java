package dataStructures;

import java.util.*;

public class List<Item> implements Iterable<Item> {

    private Node first, last, current;
    private int count, countAdd;

    /**
     * Crea los nodos y los item de tipo item
     */
    private class Node {

        Item item;
        Node next;
        Node prev;
    }

    /**
     * Constructor de a lista
     */
    public List() {
        first = null;
        last = null;
        current = null;
        count = 0;
    }

    /**
     * Añade un elemento al final de la lista
     *
     * @param element
     */
    public void add(Item element) {
        Node newElement = new Node();
        newElement.item = element;
        if (count == 0) {
            first = last = newElement;
        } else {
            last.next = newElement;
            newElement.prev = last;
            last = newElement;
            last.next = first;
            first.prev = last;
        }
        count++;
    }

    /**
     * Añade un elemento a la posición indicada
     *
     * @param element
     * @param index
     * @return
     */
    public boolean add(Item element, int index) {
        if (index < 0 || index > count) {
            throw new NoSuchElementException("Error: Índice fuera de los límites de la lista.");

        } else {
            Node newElement = new Node();
            newElement.item = element;

            current = first;
            countAdd = 0;

            while (countAdd < index) {
                current = current.next;
                countAdd++;
            }

            newElement.next = current;
            newElement.prev = current.prev;
            current.prev.next = newElement;
            current.prev = newElement;

            if (index == 0) {
                first = newElement;
            }
            if (index == count) {
                last = newElement;
            }
            count++;
            return true;
        }
    }

    /**
     * Añade un elemento al inicio de la lista
     *
     * @param item
     */
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) {
            oldfirst.prev = first;
        }
        if (last == null) {
            last = first;
        } else {
            first.prev = last;
            last.next = first;
        }
        count++;
    }

    /**
     * Elimina el primer elemento de la lista
     *
     * @return
     */
    public Item removeFirst() {
        Item item = first.item;
        first.item = null;
        first = first.next;
        if (first != null) {
            first.prev = last;
            last.next = first;
        } else {
            last = null;
        }
        count--;
        return item;
    }

    /**
     * Elimina el último elemento de la lista.
     *
     * @return
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = first;
            first.prev = last;
        }
        count--;
        return item;
    }

    /**
     * Elimina un elemento en la posición indicada.
     *
     * @param index
     * @return
     */
    public Item remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Item item = current.item;
        if (current == first) {
            removeFirst();
        } else if (current == last) {
            removeLast();
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            count--;
        }
        return item;
    }

    /**
     * Retorna el primer elemento de la lista.
     *
     * @return
     */
    public Item peek() {
        return first.item;
    }

    /**
     * Verifica si la lista está vacía
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Retorna la cantidad de elementos en la lista
     *
     * @return
     */
    public int size() {
        return count;
    }

    public Item get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public void set(int index, Item item) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.item = item;
    }

    /**
     * Imprime los elementos pero bonitos
     *
     * @return
     */
    @Override
    public String toString() {
        if (first == null) {
            return "[]";
        }

        String str = "";
        Node current = first;
        do {
            str += "[" + current.item + "]";
            current = current.next;
            if (current != first) {
                str += "<->";
            }
        } while (current != first);

        return str;
    }

    /**
     * Itera del primero al último
     *
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new LLIterator();
    }

    /**
     * Itera del último al primero
     *
     * @return
     */
    public Iterator<Item> reverseIterator() {
        return new ReverseLLIterator();
    }

    private class LLIterator implements Iterator<Item> {

        private Node current = first;

        /**
         * Verifica si tiene elementos al frente
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Avanza a través de la lista en orden
         *
         * @return
         */
        @Override
        public Item next() {
            Item item = current.item;
            current = (current == last) ? null : current.next;
            return item;
        }
    }

    private class ReverseLLIterator implements Iterator<Item> {

        private Node current = last;

        /**
         * Verifica si tiene elementos al frente
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Avanza a través de la lista en orden inverso
         *
         * @return
         */
        @Override
        public Item next() {
            Item item = current.item;
            current = (current == first) ? null : current.prev;
            return item;
        }
    }

}
