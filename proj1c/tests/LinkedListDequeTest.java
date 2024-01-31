import deque.Deque;
import deque.LinkedListDeque;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    /** This test performs the iterator() test **/
    @Test
    public void testIterator() {
        Deque<Integer> test = new LinkedListDeque<>();
        test.addLast(0);   // [0]
        test.addLast(1);   // [0, 1]
        test.addLast(-1); // [0, 1, -1]
        test.addLast(2);   // [0, 1, -1, 2]
        test.addLast(-2); // [0, 1, -1, 2, -2]

        Deque<Integer> mirror = new LinkedListDeque<>();
        for (int i: test) {
            mirror.addLast(i);
        }

        assertThat(mirror.toList()).containsExactly(0, 1, -1, 2, -2).inOrder();
    }

    @Test
    public void testEqualLinkedListDeque() {
        Deque<String> lld1 = new LinkedListDeque<>();
        Deque<String> lld2 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testToStringLinkedListDeque() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        System.out.println(lld1);
    }

}
