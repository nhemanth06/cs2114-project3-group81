import java.util.List;
 
/**
 * Model class: owns the line of waiting orders and the log of completed
 * orders. Priority orders go to the front of the line; all other orders go to
 * the back. This class never prints or reads input.
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
     * Adds an order to the line: the front if it is a priority order,
     * otherwise the back. A later priority order is therefore served before an
     * earlier one.
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
            waiting.addToFront(order);
        }
        else {
            waiting.addToBack(order);
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
 
