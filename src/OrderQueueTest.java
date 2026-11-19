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
}