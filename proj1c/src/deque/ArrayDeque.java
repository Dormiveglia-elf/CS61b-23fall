package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    protected int front;
    protected int back;
    protected int size;
    protected T[] items;

    private static final int START_SIZE = 8;
    private static final int BOUNDAY = 16;

    public ArrayDeque() {
        items = (T[]) new Object[START_SIZE];
        front = 0;
        back = 0;
        size = 0;
    }

    public ArrayDeque(int capicity) {
        items = (T[]) new Object[capicity];
        front = 0;
        back = 0;
        size = 0;
    }

    /* competible for both resizeUp and resizeDown cases */
    public void resize(int capicity) {
        T[] temp = (T[]) new Object[capicity];
        for (int i = 0; i < size; i++) {
            temp[i] = items[(front + i) % items.length];
        }
        items = temp;
        front = 0;
        back = size - 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            front = 0;
            back = 0;
            items[0] = x;
            size += 1;
            return;
        }

        if (size == items.length) {
            resize(size *  2);
        }

        if (front == 0) {
            front = items.length - 1;
        } else {
            front--;
        }
        items[front] = x;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            front = 0;
            back = 0;
            items[0] = x;
            size++;
            return;
        }

        if (size == items.length) {
            resize(size * 2);
        }

        if (back == items.length - 1) {
            back = 0;
        } else {
            back++;
        }
        items[back] = x;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            returnList.add(items[i]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (items.length >= BOUNDAY) {
            if ((size - 1) * 4 < items.length) {
                resize(size * 2);
            }
        }

        T returnVal = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size--;

        return returnVal;
    }

    @Override
    public T removeLast() {
        if (items.length >= BOUNDAY) {
            if ((size - 1) * 4 < items.length) {
                resize(size * 2);
            }
        }

        T returnVal = items[back];
        items[back] = null;
        back = (back - 1) % items.length;
        size--;

        return returnVal;
    }

    @Override
    public T get(int index) {
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for project 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int idx;

        public ArrayDequeIterator() {
            idx = 0;
        }

        @Override
        public boolean hasNext() {
            return idx < size;
        }

        @Override
        public T next() {
            T returnItem = items[idx];
            idx += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof ArrayDeque otherDeque) {
            if (this.size != otherDeque.size) {
                return false;
            }
            for (int i = 0; i < this.items.length; i++) {
                if (this.items[i] != otherDeque.items[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return (this.toList()).toString();
    }

    /* Get the first value of the deque */
    public T getFirst() {
        return get(front);
    }
}
