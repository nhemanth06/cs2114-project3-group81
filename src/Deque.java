
public class Deque<T> extends DLinkedDeque<T> {

    @Override
    public void addToFront(T newEntry) {
        // TODO Auto-generated method stub

    }


    @Override
    public void addToBack(T newEntry) {
        // TODO Auto-generated method stub
        DLNode<T> entry = new DLNode<T>(lastNode, newEntry, null);
        if (size()==0) {
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
        return null;
    }


    @Override
    public T getFront() {
        // TODO Auto-generated method stub
        return firstNode.getData();
    }


    @Override
    public T getBack() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

}
