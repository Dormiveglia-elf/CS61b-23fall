import deque.IntegerComparator;
import deque.MaxArrayDeque;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {

    @Test
    public void testMaxDeque() {
        Comparator<Integer> cd = new IntegerComparator();
        MaxArrayDeque<Integer> test = new MaxArrayDeque<>(cd);
        test.addLast(0);   // [0]
        test.addLast(1);   // [0, 1]
        test.addLast(-1); // [0, 1, -1]
        test.addLast(2);   // [0, 1, -1, 2]
        test.addLast(-2); // [0, 1, -1, 2, -2]
        test.addLast(-2); // [0, 1, -1, 2, -2, -2, -2, -2]
        test.addLast(-2); // [0, 1, -1, 2, -2]

        assertThat(test.max()).isEqualTo(2);
    }

}
