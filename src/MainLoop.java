
import java.util.Scanner;
import java.util.List;
import java.util.NoSuchElementException;

public class MainLoop {

    public static void main(String[] args) {
        OrderQueue queue = new OrderQueue();
        ConsoleView view = new ConsoleView(new Scanner(System.in));
        CafeController controller = new CafeController(queue, view);
        controller.run();
    }
}
