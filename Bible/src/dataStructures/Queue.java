package dataStructures;

import java.util.*;

public class Queue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int count;

    private class Node {

        Item item;
        Node next;
    }

    public Queue() {
        first = null;
        last = null;
        count = 0;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        count++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue vacia");
        }
        Item item = first.item;
        first = first.next;
        count--;
        if (isEmpty()) {
            last = null; // to avoid loitering
        }
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
