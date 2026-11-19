/**
 * An interface for the ADT stack (last in, first out).
 *
 * @param <T>
 *            generic type for the stack
 */
public interface StackInterface<T>
{
    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry
     *            An object to be added.
     */
    public void push(T newEntry);
 
    /**
     * Removes and returns the top entry of this stack.
     *
     * @return The object at the top of the stack.
     * @throws java.util.EmptyStackException
     *             if the stack is empty before the operation.
     */
    public T pop();
 
    /**
     * Retrieves the top entry of this stack without removing it.
     *
     * @return The object at the top of the stack.
     * @throws java.util.EmptyStackException
     *             if the stack is empty before the operation.
     */
    public T peek();
 
    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty, or false otherwise.
     */
    public boolean isEmpty();
 
    /**
     * Removes all entries from this stack.
     */
    public void clear();
}
 
