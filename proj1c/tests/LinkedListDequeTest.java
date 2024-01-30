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

}
