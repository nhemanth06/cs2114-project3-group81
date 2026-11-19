import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for OrderQueue. No console or view is involved: the model is testable
 * on its own. (A, B = regular orders; P, P2 = priority orders.)
 */
class OrderQueueTest
{

    //~ Fields ................................................................

    private OrderQueue queue;
    private DrinkOrder a;
    private DrinkOrder b;
    private DrinkOrder p;
    private DrinkOrder p2;

    //~ Setup .................................................................

    @BeforeEach
    void setUp() {
        queue = new OrderQueue();
        a = new DrinkOrder("Ann", "Latte", "M", false);
        b = new DrinkOrder("Ben", "Mocha", "L", false);
        p = new DrinkOrder("Pat", "Espresso", "S", true);
        p2 = new DrinkOrder("Pia", "Americano", "S", true);
    }

    //~ Public Methods ........................................................

    // ---- enqueue ----

    @Test
    void enqueueRegularOrdersGoToBack() {
        queue.enqueue(a);
        queue.enqueue(b);
        assertEquals(a, queue.peek());
        assertEquals(2, queue.waitingCount());
        assertEquals(List.of(a, b), queue.getWaiting());
    }


    @Test
    void enqueuePriorityOrderGoesToFront() {
        queue.enqueue(a);
        queue.enqueue(p);
        assertEquals(p, queue.peek());
        assertEquals(List.of(p, a), queue.getWaiting());
    }


    @Test
    void enqueueNullThrowsAndLineUnchanged() {
        queue.enqueue(a);
        assertThrows(IllegalArgumentException.class,
            () -> queue.enqueue(null));
        assertEquals(1, queue.waitingCount());
        assertEquals(List.of(a), queue.getWaiting());
    }


    @Test
    void oldestPriorityOrderIsServedBeforeNewerPriorityOrder() {
        queue.enqueue(p);
        queue.enqueue(p2);
        assertEquals(p, queue.peek());
        assertEquals(List.of(p, p2), queue.getWaiting());
    }


    @Test
    void priorityOrderGoesBehindOlderPriorityButAheadOfRegular() {
        queue.enqueue(a);
        queue.enqueue(p);
        queue.enqueue(b);
        queue.enqueue(p2);
        assertEquals(List.of(p, p2, a, b), queue.getWaiting());
    }


    @Test
    void manyPriorityOrdersKeepArrivalOrder() {
        DrinkOrder p3 = new DrinkOrder("Pam", "Mocha", "M", true);
        queue.enqueue(a);
        queue.enqueue(p);
        queue.enqueue(p2);
        queue.enqueue(p3);
        assertEquals(List.of(p, p2, p3, a), queue.getWaiting());
        assertEquals(p, queue.dequeue());
        assertEquals(p2, queue.dequeue());
        assertEquals(p3, queue.dequeue());
        assertEquals(a, queue.dequeue());
    }


    @Test
    void priorityOrderAfterServingSomeStillGoesBehindOlderPriority() {
        queue.enqueue(p);
        queue.enqueue(a);
        queue.enqueue(p2);              // line: [p, p2, a]
        assertEquals(p, queue.dequeue());
        DrinkOrder p3 = new DrinkOrder("Pam", "Mocha", "M", true);
        queue.enqueue(p3);              // line: [p2, p3, a]
        assertEquals(List.of(p2, p3, a), queue.getWaiting());
    }


    @Test
    void regularOrderNeverServedWhilePriorityOrderWaits() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.enqueue(p);
        assertEquals(p, queue.dequeue());
        queue.enqueue(p2);              // arrives while a and b are waiting
        assertEquals(p2, queue.dequeue());
        assertEquals(a, queue.dequeue());
        assertEquals(b, queue.dequeue());
    }


    // ---- dequeue ----

    @Test
    void dequeueReturnsFrontAndMovesItToCompleted() {
        queue.enqueue(a);
        queue.enqueue(b);
        assertEquals(a, queue.dequeue());
        assertEquals(List.of(b), queue.getWaiting());
        assertEquals(List.of(a), queue.getCompleted());
        assertEquals(1, queue.completedCount());
    }


    @Test
    void dequeueOnEmptyThrowsAndCompletedCountStaysZero() {
        for (int i = 0; i < 3; i++) {
            assertThrows(EmptyQueueException.class, () -> queue.dequeue());
        }
        assertEquals(0, queue.completedCount());
        assertEquals(0, queue.waitingCount());
    }


    // ---- peek ----

    @Test
    void peekReturnsFrontWithoutRemoving() {
        queue.enqueue(a);
        queue.enqueue(b);
        assertEquals(a, queue.peek());
        assertEquals(a, queue.peek());
        assertEquals(2, queue.waitingCount());
    }


    @Test
    void peekOnEmptyThrows() {
        assertThrows(EmptyQueueException.class, () -> queue.peek());
        assertEquals(0, queue.waitingCount());
    }


    // ---- isEmpty ----

    @Test
    void isEmptyTrueForNewQueueThenFalseAfterEnqueue() {
        assertTrue(queue.isEmpty());
        queue.enqueue(a);
        assertFalse(queue.isEmpty());
    }


    @Test
    void isEmptyTrueAgainOnceEveryOrderIsCompleted() {
        queue.enqueue(a);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }


    // ---- waitingCount / completedCount ----

    @Test
    void waitingCountCountsEnqueuedOrders() {
        assertEquals(0, queue.waitingCount());
        queue.enqueue(a);
        queue.enqueue(b);
        queue.enqueue(p);
        assertEquals(3, queue.waitingCount());
    }


    @Test
    void waitingCountUnchangedByFailedCalls() {
        assertThrows(EmptyQueueException.class, () -> queue.dequeue());
        assertThrows(EmptyQueueException.class, () -> queue.peek());
        assertEquals(0, queue.waitingCount());
    }


    @Test
    void completedCountCountsDequeuedOrders() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.dequeue();
        queue.dequeue();
        assertEquals(2, queue.completedCount());
        assertEquals(0, queue.waitingCount());
    }


    @Test
    void completedCountUnchangedByFailedDequeue() {
        assertThrows(EmptyQueueException.class, () -> queue.dequeue());
        assertEquals(0, queue.completedCount());
    }


    // ---- getWaiting / getCompleted ----

    @Test
    void getWaitingIsFrontToBackAndDoesNotChangeQueue() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.enqueue(p);
        assertEquals(List.of(p, a, b), queue.getWaiting());
        assertEquals(3, queue.waitingCount());
        assertEquals(p, queue.peek());
    }


    @Test
    void getWaitingIsACopy() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.getWaiting().remove(0);
        assertEquals(2, queue.waitingCount());
        assertEquals(List.of(a, b), queue.getWaiting());
    }


    @Test
    void getCompletedIsInCompletionOrder() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.enqueue(p);       // line: [p, a, b]
        queue.dequeue();
        queue.dequeue();
        assertEquals(List.of(p, a), queue.getCompleted());
    }


    @Test
    void getCompletedIsACopy() {
        queue.enqueue(a);
        queue.dequeue();
        queue.getCompleted().clear();
        assertEquals(1, queue.completedCount());
        assertEquals(List.of(a), queue.getCompleted());
    }
}