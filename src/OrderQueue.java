import java.util.List;

/**
 * Model class: owns the line of waiting orders and the log of completed
 * orders. The line always has two parts: all priority orders first, then all
 * regular orders, and each part is in arrival order. So the oldest priority
 * order is always served first, and no regular order is served while a
 * priority order is waiting. This class never prints or reads input.
 */
public class OrderQueue {

    //~ Fields ................................................................

    /** Orders still waiting. Front of the deque = next to be served. */
    private final Deque<DrinkOrder> waiting;

    /** Orders already served, in the order they were completed. */
    private final Deque<DrinkOrder> completed;

    //~ Constructors ..........................................................

    /**
     * Create an empty OrderQueue.
     */
    public OrderQueue() {
        waiting = new Deque<DrinkOrder>();
        completed = new Deque<DrinkOrder>();
    }

    //~ Public Methods ........................................................

    /**
     * Adds an order to the line. A regular order goes to the back. A priority
     * order goes behind the priority orders already waiting but ahead of every
     * regular order, so the oldest priority order is always served first.
     *
     * @param order
     *            the order to add
     * @throws IllegalArgumentException
     *             if order is null (the line is left unchanged)
     */
    public void enqueue(DrinkOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (order.getPriority()) {
            addPriority(order);
        }
        else {
            waiting.addToBack(order);
        }
    }


    /**
     * Inserts a priority order after the last priority order already waiting.
     * The deque can only add at its two ends, so the waiting priority orders
     * are lifted off the front onto a stack, the new order is put at the
     * front, and the stack is unloaded back onto the front. A stack reverses
     * what is pushed onto it, so unloading restores the original order and the
     * new order lands behind the older ones. This works because priority
     * orders are always a block at the front of the line.
     * <p>
     * Takes time proportional to the number of waiting priority orders.
     *
     * @param order
     *            the priority order to insert
     */
    private void addPriority(DrinkOrder order) {
        LinkedStack<DrinkOrder> holding = new LinkedStack<DrinkOrder>();
        while (!waiting.isEmpty() && waiting.getFront().getPriority()) {
            holding.push(waiting.removeFront());
        }
        waiting.addToFront(order);
        while (!holding.isEmpty()) {
            waiting.addToFront(holding.pop());
        }
    }


    /**
     * Completes the next order: removes it from the front of the line, records
     * it as completed, and returns it.
     *
     * @return the order that was completed
     * @throws EmptyQueueException
     *             if no orders are waiting (nothing changes)
     */
    public DrinkOrder dequeue() {
        DrinkOrder order = waiting.removeFront();
        completed.addToBack(order);
        return order;
    }


    /**
     * Returns the next order without removing it.
     *
     * @return the order at the front of the line
     * @throws EmptyQueueException
     *             if no orders are waiting
     */
    public DrinkOrder peek() {
        return waiting.getFront();
    }


    /**
     * @return true if no orders are waiting
     */
    public boolean isEmpty() {
        return waiting.isEmpty();
    }


    /**
     * @return the number of waiting orders
     */
    public int waitingCount() {
        return waiting.size();
    }


    /**
     * @return the number of completed orders
     */
    public int completedCount() {
        return completed.size();
    }


    /**
     * @return a copy of the waiting orders, front to back; changing the copy
     *         does not change the line
     */
    public List<DrinkOrder> getWaiting() {
        return waiting.toList();
    }


    /**
     * @return a copy of the completed orders in completion order; changing the
     *         copy does not change the log
     */
    public List<DrinkOrder> getCompleted() {
        return completed.toList();
    }
}
 
