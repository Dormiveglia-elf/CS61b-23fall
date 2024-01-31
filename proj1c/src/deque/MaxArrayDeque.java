package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque{
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        int n = super.items.length;
        T result = (T) super.items[front];
        int pos = front;
        for (int i = 0; i < super.size; i++) {
            T temp = (T) super.items[pos % n];
            if (comparator.compare(result, temp) <= 0){
                result = temp;
            }
            pos++;
        }

        return result;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {return null;}

        int n = super.items.length;
        T result = (T) super.items[front];
        int pos = front;
        for (int i = 0; i < super.size; i++) {
            T temp = (T) super.items[pos % n];
            if (c.compare(result, temp) <= 0){
                result = temp;
            }
            pos++;
        }

        return result;
    }
}
