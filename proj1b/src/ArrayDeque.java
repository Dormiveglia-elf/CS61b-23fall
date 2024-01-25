import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private int front;
    private int back;
    private int size;
    private T[] items;

    private static final int START_SIZE = 8;

    public ArrayDeque(){
        items = (T[]) new Object[START_SIZE];
        front = 0;
        back = 0;
        size = 0;
    }

    public void resizeUp(int capicity) {
        T[] temp = (T[]) new Object[capicity];
        for (int i = 0; i < size; i++) {
            temp[i] = items[(front+i) % size];
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
            resizeUp(size *  2);
        }

        if (front == 0){
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
            resizeUp(size * 2);
        }

        if (back == items.length-1) {
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
        for (int i = 0; i < items.length; i++){
            returnList.add(items[i]);
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


        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
