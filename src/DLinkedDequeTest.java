import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Deque class (the concrete DLinkedDeque).
 */
class DLinkedDequeTest
{

    //~ Fields ................................................................

    private Deque<String> deque;

    //~ Setup .................................................................

    @BeforeEach
    void setUp() {
        deque = new Deque<String>();
    }

    //~ Public Methods ........................................................

    // ---- new deque ----

    @Test
    void newDequeIsEmpty() {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertEquals(List.of(), deque.toList());
    }


    // ---- addToFront / addToBack ----

    @Test
    void addToFrontOnEmpty() {
        deque.addToFront("A");
        assertEquals("A", deque.getFront());
        assertEquals("A", deque.getBack());
        assertEquals(1, deque.size());
    }


    @Test
    void addToBackOnEmpty() {
        deque.addToBack("A");
        assertEquals("A", deque.getFront());
        assertEquals("A", deque.getBack());
        assertEquals(1, deque.size());
    }


    @Test
    void addToFrontPutsItemInFront() {
        deque.addToBack("A");
        deque.addToFront("B");
        assertEquals(List.of("B", "A"), deque.toList());
    }


    @Test
    void addToBackPutsItemInBack() {
        deque.addToFront("A");
        deque.addToBack("B");
        assertEquals(List.of("A", "B"), deque.toList());
    }


    // ---- getFront / getBack ----

    @Test
    void getDoesNotRemove() {
        deque.addToBack("A");
        deque.addToBack("B");
        assertEquals("A", deque.getFront());
        assertEquals("B", deque.getBack());
        assertEquals(2, deque.size());
    }


    @Test
    void getFrontOnEmptyThrows() {
        assertThrows(EmptyQueueException.class, () -> deque.getFront());
    }


    @Test
    void getBackOnEmptyThrows() {
        assertThrows(EmptyQueueException.class, () -> deque.getBack());
    }


    // ---- removeFront / removeBack ----

    @Test
    void removeFrontReturnsFront() {
        deque.addToBack("A");
        deque.addToBack("B");
        assertEquals("A", deque.removeFront());
        assertEquals(List.of("B"), deque.toList());
        assertEquals(1, deque.size());
    }


    @Test
    void removeBackReturnsBack() {
        deque.addToBack("A");
        deque.addToBack("B");
        assertEquals("B", deque.removeBack());
        assertEquals(List.of("A"), deque.toList());
        assertEquals(1, deque.size());
    }


    @Test
    void removingOnlyItemEmptiesDeque() {
        deque.addToFront("A");
        assertEquals("A", deque.removeFront());
        assertTrue(deque.isEmpty());

        deque.addToFront("B");
        assertEquals("B", deque.removeBack());
        assertTrue(deque.isEmpty());
    }


    @Test
    void removeFrontOnEmptyThrowsAndSizeStaysZero() {
        assertThrows(EmptyQueueException.class, () -> deque.removeFront());
        assertEquals(0, deque.size());
    }


    @Test
    void removeBackOnEmptyThrowsAndSizeStaysZero() {
        assertThrows(EmptyQueueException.class, () -> deque.removeBack());
        assertEquals(0, deque.size());
    }


    // ---- links stay consistent after mixed operations ----

    @Test
    void mixedOperations() {
        Deque<Integer> nums = new Deque<Integer>();
        nums.addToBack(1);
        nums.addToBack(2);
        nums.addToFront(0);                     // [0, 1, 2]
        assertEquals(2, nums.removeBack());     // [0, 1]
        assertEquals(0, nums.removeFront());    // [1]
        nums.addToFront(-1);                    // [-1, 1]
        nums.addToBack(5);                      // [-1, 1, 5]
        assertEquals(List.of(-1, 1, 5), nums.toList());
        assertEquals(3, nums.size());
        assertEquals(-1, nums.getFront());
        assertEquals(5, nums.getBack());
    }


    // ---- clear ----

    @Test
    void clearEmptiesDequeAndItCanBeReused() {
        deque.addToBack("A");
        deque.addToBack("B");
        deque.clear();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertThrows(EmptyQueueException.class, () -> deque.getFront());

        deque.addToBack("C");
        assertEquals(List.of("C"), deque.toList());
    }


    @Test
    void clearOnEmptyDoesNothing() {
        deque.clear();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }


    // ---- toList is a copy ----

    @Test
    void toListIsACopy() {
        deque.addToBack("A");
        List<String> items = deque.toList();
        items.clear();
        assertEquals(1, deque.size());
        assertEquals(List.of("A"), deque.toList());
    }
}
