import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DLinkedDequeTest
{

   
    
    private Deque<String> deque;
    
    
    void setUp() {
        deque = new Deque<String>();
    }
    
    @Test
    void addFirstOnEmpty() {
        deque.addToFront("A");
        assertEquals("A", deque.getFront());
        assertEquals("A", deque.getBack());
        assertEquals(1, deque.size());
    
    
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................

    }
}
