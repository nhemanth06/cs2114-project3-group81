import java.util.List;
import java.util.ArrayList;


public class Deque<T> extends DLinkedDeque<T> {

    @Override
    public void addToFront(T newEntry) {
        // TODO Auto-generated method stub
        DLNode<T> entry = new DLNode<T>(null, newEntry, firstNode);
        if (isEmpty()) {
            lastNode = entry;
        }
        else {
            firstNode.setPreviousNode(entry);
        }
        firstNode = entry;
        size++;
    }


    @Override
    public void addToBack(T newEntry) {
        // TODO Auto-generated method stub
        DLNode<T> entry = new DLNode<T>(lastNode, newEntry, null);
        if (size() == 0) {
            firstNode = entry;
        }
        else {
            lastNode.setNextNode(entry);
        }
        lastNode = entry;
        size++;
    }


    @Override
    public T removeFront() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new EmptyQueueException("There are no orders in line.");
        }
        T first = getFront();
        firstNode = firstNode.getNextNode();
        if (firstNode == null) {
            lastNode = null;
        }
        else {
            firstNode.setPreviousNode(null);
        }
        size--;
        return first;
    }


    @Override
    public T removeBack() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new EmptyQueueException("There are no orders in line.");
        }
        T last = getBack();
        lastNode = lastNode.getPreviousNode();
        if (lastNode == null) {
            firstNode = null;
        }
        else {
            lastNode.setNextNode(null);
        }
        size--;
        return last;
    }


    @Override
    public T getFront() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new EmptyQueueException("There are no orders in line.");
        }
        return firstNode.getData();
    }


    @Override
    public T getBack() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new EmptyQueueException("There are no orders in line.");
        }
        return lastNode.getData();
    }


    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size == 0;
    }


    @Override
    public void clear() {
        // TODO Auto-generated method stub
        while (lastNode != null) {
            T out = removeFront();
        }
    }
        
        /**
         * Copy of the entries from front to back. Does not change the deque, so
         * it can be used to display waiting orders without removing them.
         *
         * @return a new list holding the entries in order
         */
        public List<T> toList() {
            List<T> items = new ArrayList<T>();
            DLNode<T> current = firstNode;
            while (current != null) {
                items.add(current.getData());
                current = current.getNextNode();
            }
            return items;
        } 
    
}
