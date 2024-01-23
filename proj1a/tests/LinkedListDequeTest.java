import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

//     Below, you'll write your own tests for LinkedListDeque.

    @Test
    /** This Test test the isEmpty() function. */
    public void testIsEmpty() {
        Deque<Integer> test = new LinkedListDeque<>();
        boolean flag = test.isEmpty();
        assertThat(flag).isTrue();

        test.addFirst(0);
        flag = test.isEmpty();
        assertThat(flag).isFalse();
    }

    @Test
    /** This Test test the size() method */
    public void testSize() {
        Deque<Integer> test = new LinkedListDeque<>();
        assertThat(test.size()).isEqualTo(0);
        test.addLast(0);   // [0]
        test.addLast(1);   // [0, 1]
        test.addFirst(-1); // [-1, 0, 1]
        assertThat(test.size()).isEqualTo(3);
        test.addLast(2);   // [-1, 0, 1, 2]
        test.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(test.size()).isEqualTo(5);
    }

    @Test
    /** This Test test the get() method */
    public void testGet() {
        Deque<Integer> test = new LinkedListDeque<>();
        test.addLast(0);   // [0]
        test.addLast(1);   // [0, 1]
        test.addFirst(-1); // [-1, 0, 1]
        test.addLast(2);   // [-1, 0, 1, 2]
        test.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(test.get(888)).isEqualTo(null);
        assertThat(test.get(-1)).isEqualTo(null);
        assertThat(test.get(0)).isEqualTo(-2);
        assertThat(test.get(2)).isEqualTo(0);
        assertThat(test.get(4)).isEqualTo(2);
    }

    @Test
    /** This Test test the getRecursive() method */
    public void testRecursiveGet() {
        Deque<Integer> test = new LinkedListDeque<>();
        test.addLast(0);   // [0]
        test.addLast(1);   // [0, 1]
        test.addFirst(-1); // [-1, 0, 1]
        test.addLast(2);   // [-1, 0, 1, 2]
        test.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(test.getRecursive(888)).isEqualTo(null);
        assertThat(test.getRecursive(-1)).isEqualTo(null);
        assertThat(test.getRecursive(0)).isEqualTo(-2);
        assertThat(test.getRecursive(2)).isEqualTo(0);
        assertThat(test.getRecursive(4)).isEqualTo(2);
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void removeFirstAndRemoveLastTest() {
        Deque<Integer> test = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        test.addLast(0);   // [0]
        test.addLast(1);   // [0, 1]
        test.addFirst(-1); // [-1, 0, 1]
        test.addLast(2);   // [-1, 0, 1, 2]
        test.addFirst(-2); // [-2, -1, 0, 1, 2]

        test.removeFirst();
        assertThat(test.toList()).containsExactly(-1, 0, 1, 2).inOrder();

        test.removeLast();
        assertThat(test.toList()).containsExactly(-1, 0, 1).inOrder();

        test.removeFirst();
        assertThat(test.toList()).containsExactly(0, 1).inOrder();

        test.removeLast();
        assertThat(test.toList()).containsExactly(0).inOrder();

        test.removeLast();
        assertThat(test.removeFirst()).isEqualTo(null);
    }
}