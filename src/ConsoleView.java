
import java.util.*;

/**
 * Controller: runs the menu loop, turns the user's raw text into calls on the
 * model (OrderQueue and DrinkOrder), catches errors, and tells the view what
 * to show. It holds no order data of its own.
 */
public class ConsoleView {

    private final Scanner scan;

    //~ Constructors ..........................................................

    /**
     * Create a view that reads from the given Scanner.
     *
     * @param scan
     *            the source of user input
     */
    public ConsoleView(Scanner scan) {
        this.scan = scan;
    }

    //~ Public Methods ........................................................

    /**
     * Prints the main menu commands.
     */
    public void showMainMenu() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("  1 - Add an order");
        System.out.println("  2 - See the next order");
        System.out.println("  3 - Complete the next order");
        System.out.println("  4 - See completed orders");
        System.out.println("  5 - See waiting/completed counts");
        System.out.println("  0 - Exit");
    }


    /**
     * Prints the drinks as a numbered list.
     *
     * @param drinks
     *            the drink names, in menu order
     */
    public void showDrinkMenu(List<String> drinks) {
        System.out.println("Choose a drink:");
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println("  " + (i + 1) + " - " + drinks.get(i));
        }
    }


    /**
     * Prints a message (no newline) and returns the raw line the user types.
     *
     * @param message
     *            the prompt to show
     * @return the text typed, unchecked
     * @throws java.util.NoSuchElementException
     *             if there is no more input
     */
    public String prompt(String message) {
        System.out.print(message);
        return scan.nextLine();
    }


    /**
     * Prints one order with a label, e.g. "Next order: Ann - M Latte".
     *
     * @param label
     *            text shown before the order
     * @param order
     *            the order to show
     */
    public void showOrder(String label, DrinkOrder order) {
        System.out.println(label + ": " + order);
    }


    /**
     * Prints a titled, numbered list of orders.
     *
     * @param title
     *            heading for the list
     * @param orders
     *            the orders to show, in order
     */
    public void showOrderList(String title, List<DrinkOrder> orders) {
        System.out.println(title);
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + orders.get(i));
        }
    }


    /**
     * Prints the waiting and completed counts.
     *
     * @param waiting
     *            number of waiting orders
     * @param completed
     *            number of completed orders
     */
    public void showCounts(int waiting, int completed) {
        System.out.println("Waiting: " + waiting);
        System.out.println("Completed: " + completed);
    }


    /**
     * Prints a normal message.
     *
     * @param text
     *            the message
     */
    public void showMessage(String text) {
        System.out.println(text);
    }


    /**
     * Prints an error message.
     *
     * @param text
     *            what went wrong
     */
    public void showError(String text) {
        System.out.println("Error: " + text);
    }
}
