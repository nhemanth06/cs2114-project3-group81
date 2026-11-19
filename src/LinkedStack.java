import java.util.EmptyStackException;

/**
 * A class that implements the ADT stack by using a singly linked chain of
 * nodes. The top of the stack is the first node of the chain, so push, pop,
 * and peek are all O(1).
 *
 * @param <T>
 *            generic type for the stack
 */
public class LinkedStack<T> implements StackInterface<T>
{
    //~ Fields ................................................................

    /** References the node at the top of the stack. */
    private Node<T> topNode;

    /** Number of entries in the stack. */
    private int size;

    //~ Constructors ..........................................................

    /**
     * Create an empty LinkedStack.
     */
    public LinkedStack()
    {
        topNode = null;
        size = 0;
    }

    //~ Public Methods ........................................................

    @Override
    public void push(T newEntry)
    {
        topNode = new Node<T>(newEntry, topNode);
        size++;
    }


    @Override
    public T pop()
    {
        T top = peek();
        topNode = topNode.next;
        size--;
        return top;
    }


    @Override
    public T peek()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        return topNode.data;
    }


    @Override
    public boolean isEmpty()
    {
        return topNode == null;
    }


    @Override
    public void clear()
    {
        topNode = null;
        size = 0;
    }


    /**
     * Get the number of entries in this stack. Does not alter the stack.
     *
     * @return the number of entries
     */
    public int size()
    {
        return size;
    }

    //~ Inner Classes .........................................................

    /**
     * One link in the chain: an entry and the node below it.
     *
     * @param <T>
     *            type of the entry
     */
    private static final class Node<T>
    {
        private final T data;
        private final Node<T> next;

        private Node(T data, Node<T> next)
        {
            this.data = data;
            this.next = next;
        }
    }
}