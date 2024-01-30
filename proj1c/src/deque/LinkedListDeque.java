package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T>{
    public class Node {
        private Node prev;
        public T item;
        private Node next;

        public Node (T item) {
            this.item = item;
            this.next = this;
            this.prev = this;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        public Node getNext() {
            return this.next;
        }

        public void setPrev(Node node) {
            this.prev = node;
        }

        public Node getPrev(){
            return this.prev;
        }
    }

    Node sentinel;
    private int size = 0;
    public LinkedListDeque(){
        sentinel = new Node(null);
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x);
        newNode.setNext(sentinel.getNext());
        newNode.setPrev(sentinel);
        sentinel.getNext().setPrev(newNode);
        sentinel.setNext(newNode);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x);
        newNode.setNext(sentinel);
        newNode.setPrev(sentinel.getPrev());
        sentinel.getPrev().setNext(newNode);
        sentinel.setPrev(newNode);
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node iter = sentinel.next;
        for (int i = 0; i < size; i++){
            returnList.add(iter.item);
            iter = iter.getNext();
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size != 0) {
            Node removeNode = sentinel.getNext();
            sentinel.setNext(removeNode.getNext());
            removeNode.getNext().setPrev(sentinel);
            size--;
            return removeNode.item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size != 0) {
            Node removeNode = sentinel.getPrev();
            sentinel.setPrev(removeNode.getPrev());
            removeNode.getPrev().setNext(sentinel);
            size--;
            return removeNode.item;
        }
        return null;
    }

    @Override
    public T get(int index) {
        Node iter = sentinel.next;
        for (int i = 0; i < size; i++){
            if (index == i)
                return iter.item;
            iter = iter.next;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            return getRecursive(index, sentinel.next);
        }
    }

    // Add a helper function to recursively get the element
    private T getRecursive(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    /* Implement the iterator() method */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int idx;

        public LinkedListDequeIterator() {
            idx = 0;
        }

        @Override
        public boolean hasNext() {
            return idx < size;
        }

        @Override
        public T next() {
            T returnItem = get(idx);
            idx += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (other instanceof LinkedListDeque otherDeque) {
            if (this.size != otherDeque.size) {return false;}
            for (int i = 0; i < size; i++) {
                if (this.get(i) != otherDeque.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
