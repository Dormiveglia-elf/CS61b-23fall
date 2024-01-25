import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    // @Test
    // @DisplayName("ArrayDeque has no fields besides backing array and primitives")
    // void noNonTrivialFields() {
    //     List<Field> badFields = Reflection.getFields(ArrayDeque.class)
    //             .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
    //             .toList();

    //     assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    // }
    @Test
    public void testSize() {
        Deque<String> test = new ArrayDeque<>();
        assertThat(test.size()).isEqualTo(0);
        test.addLast("Hello");   // [Hello, "", "", "", "", "", "", ""]
        test.addLast("World");   // [Hello, "World", "", "", "", "", "", ""]
        test.addFirst("Java"); // [Hello, "World", "Java", "", "", "", "", ""]
        assertThat(test.size()).isEqualTo(3);
    }

    @Test
    public void testAddFirstBasic() {
        Deque<Integer> test = new ArrayDeque<>();
        test.addFirst(8);   // [8, 0, 0, 0, 0, 0, 0, 0]
        assertThat(test.get(0)).isEqualTo(8);
        test.addFirst(24);   // [8, 0, 0, 0, 0, 0, 0, 24]
        test.addFirst(33); // [8, 0, 0, 0, 0, 0, 33, 24]
        assertThat(test.get(6)).isEqualTo(33);
        test.addFirst(41);   // [8, 0, 0, 0, 0, 41, 33, 24]
        test.addFirst(23); // [8, 0, 0, 0, 23, 41, 33, 24]
        assertThat(test.get(4)).isEqualTo(23);
        assertThat(test.get(5)).isEqualTo(41);
    }

    @Test
    public void testAddLastBasic() {
        Deque<Integer> test = new ArrayDeque<>();
        test.addLast(8);   // [8, 0, 0, 0, 0, 0, 0, 0]
        assertThat(test.get(0)).isEqualTo(8);
        test.addLast(24);   // [8, 24, 0, 0, 0, 0, 0, 0]
        test.addLast(33); // [8, 24,33, 0, 0, 0, 0, 0]
        assertThat(test.get(6)).isEqualTo(null);
        test.addLast(41);   // [8, 24, 33, 41, 0, 0, 0, 0]
        test.addLast(23); // [8, 24, 33, 41, 23, 0, 0, 0]
        assertThat(test.get(4)).isEqualTo(23);
        assertThat(test.get(1)).isEqualTo(24);
    }

    @Test
    public void addFirstAndAddLastTest(){
        Deque<Integer> test = new ArrayDeque<>();
        test.addLast(8);   // [8, 0, 0, 0, 0, 0, 0, 0]
        assertThat(test.toList()).containsExactly(8, null, null, null, null, null, null, null).inOrder();
        test.addFirst(24);   // [8, 0, 0, 0, 0, 0, 0, 24]
        test.addLast(33); // [8, 33, 0, 0, 0, 0, 0, 24]
        assertThat(test.toList()).containsExactly(8, 33, null, null, null, null, null, 24).inOrder();
        test.addLast(41);   // [8, 33, 41, 0, 0, 0, 0, 24]
        test.addFirst(23); // [8, 33, 41, 0, 0, 0, 23, 24]
        assertThat(test.toList()).containsExactly(8, 33, 41, null, null, null, 23, 24).inOrder();

        /* Test the case that resize up the Array Deque */
        test.addFirst(1);
        test.addLast(1);
        test.addLast(1);
        test.addFirst(1); // [1, 23, 24, 8, 33, 41, 1, 1, null, null, null, null, null, null, null, 1]
        assertThat(test.toList())
                .containsExactly(1, 23, 24, 8, 33, 41, 1, 1, null, null, null, null, null, null, null, 1)
                .inOrder();
        test.addLast(1); // [1, 23, 24, 8, 33, 41, 1, 1, 1, null, null, null, null, null, null, 1]
        assertThat(test.toList())
                .containsExactly(1, 23, 24, 8, 33, 41, 1, 1, 1, null, null, null, null, null, null, 1)
                .inOrder();

    }

    @Test
    public void getTest(){
        Deque<Integer> test = new ArrayDeque<>();
        test.addLast(8);   // [8, 0, 0, 0, 0, 0, 0, 0]
        test.addFirst(24);   // [8, 0, 0, 0, 0, 0, 0, 24]
        test.addLast(33); // [8, 33, 0, 0, 0, 0, 0, 24]
        test.addLast(41);   // [8, 33, 41, 0, 0, 0, 0, 24]
        test.addFirst(23); // [8, 33, 41, 0, 0, 0, 23, 24]
        /* Test the case that resize up the Array Deque */
        test.addFirst(1);
        test.addLast(1);
        test.addLast(1);
        test.addFirst(1); // [1, 23, 24, 8, 33, 41, 1, 1, null, null, null, null, null, null, null, 1]
        test.addLast(1); // [1, 23, 24, 8, 33, 41, 1, 1, null, null, null, null, null, null, null, 1]
        assertThat(test.get(8)).isEqualTo(1);
        assertThat(test.get(9)).isEqualTo(null);
    }

    @Test
    public void isEmptyAndSizeTest(){
        Deque<Integer> test = new ArrayDeque<>();
        assertThat(test.isEmpty()).isTrue();
        assertThat(test.size()).isEqualTo(0);

        test.addLast(8);   // [8, 0, 0, 0, 0, 0, 0, 0]
        test.addFirst(24);   // [8, 0, 0, 0, 0, 0, 0, 24]
        assertThat(test.isEmpty()).isFalse();
        assertThat(test.size()).isEqualTo(2);
    }

    @Test
    public void ToListTest(){
        Deque<Integer> test = new ArrayDeque<>();
        test.addLast(8);   // [8, 0, 0, 0, 0, 0, 0, 0]
        test.addFirst(24);   // [8, 0, 0, 0, 0, 0, 0, 24]
        test.addLast(33); // [8, 33, 0, 0, 0, 0, 0, 24]
        test.addLast(41);   // [8, 33, 41, 0, 0, 0, 0, 24]
        test.addFirst(23); // [8, 33, 41, 0, 0, 0, 23, 24]
        /* Test the case that resize up the Array Deque */
        test.addFirst(1);
        test.addLast(1);
        test.addLast(1);
        test.addFirst(1); // [1, 23, 24, 8, 33, 41, 1, 1, null, null, null, null, null, null, null, 1]
        test.addLast(1); // [1, 23, 24, 8, 33, 41, 1, 1, 1, null, null, null, null, null, null, 1]
        assertThat(test.toList())
                .containsExactly(1, 23, 24, 8, 33, 41, 1, 1, 1, null, null, null, null, null, null, 1)
                .inOrder();

    }

    @Test
    public void  ReomoveFirstAndRemoveLastTest() {
        Deque<Integer> test = new ArrayDeque<>();
        test.addLast(8);   // [8, 0, 0, 0, 0, 0, 0, 0]
        test.addFirst(24);   // [8, 0, 0, 0, 0, 0, 0, 24]
        test.addLast(33); // [8, 33, 0, 0, 0, 0, 0, 24]
        test.addLast(41);   // [8, 33, 41, 0, 0, 0, 0, 24]
        test.addFirst(23); // [8, 33, 41, null, null, null, 23, 24]
        test.removeFirst(); // [8, 33, 41, null, null, null, null, 24]
        test.removeFirst(); // [8, 33, 41, null, null, null, null, null]
        test.removeFirst(); // [null, 33, 41, null, null, null, null, null]
        test.removeFirst(); // [null, null, 41, null, null, null, null, null]
        assertThat(test.toList())
                .containsExactly(null, null, 41, null, null, null, null, null)
                .inOrder();
        test.removeLast();
        assertThat(test.toList())
                .containsExactly(null, null, null, null, null, null, null, null)
                .inOrder();
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(5);
        test.addFirst(6);
        test.addFirst(7);
        test.addFirst(8);
        test.addFirst(9);
        test.addFirst(10);
        test.addFirst(11);
        test.addFirst(12);
        test.addFirst(13);
        test.addFirst(14);
        test.addFirst(15);
        test.addFirst(16);
        test.addFirst(17);
        assertThat(test.toList())
                .containsExactly(16, 15, 14, 13, 12, 11, 10, 9,
                                            8, 7, 6, 5, 4, 3, 2, 1,
                                            null, null, null, null, null, null, null, null,
                                            null, null, null, null, null, null, null, 17)
                .inOrder();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeLast();
        assertThat(test.toList())
                .containsExactly(8, 7, 6, 5, 4, 3, 2, null, null, null, null, null, null, null, null, null)
                .inOrder();
    }

}
