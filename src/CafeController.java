import java.util.List;
import java.util.NoSuchElementException;


public class CafeController
{
    private final OrderQueue queue;
    private final ConsoleView view;

    //~ Constructors ..........................................................

    /**
     * Create a controller.
     *
     * @param queue
     *            the model
     * @param view
     *            the view
     */
    public CafeController(OrderQueue queue, ConsoleView view) {
        this.queue = queue;
        this.view = view;
    }

    /**
     * Main loop: show the menu, read a command, carry it out, and repeat
     * until the user exits (or the input runs out).
     */
    public void run() {
        try {
            boolean exit = false;
            while (!exit) {
                view.showMainMenu();
                int choice;
                try {
                    choice = parseChoice(view.prompt("Choice: "), 0, 5);
                }
                catch (IllegalArgumentException e) {
                    view.showError(e.getMessage());
                    continue;
                }

                if (choice == 0) {
                    exit = true;
                }
                else if (choice == 1) {
                    handleAddOrder();
                }
                else if (choice == 2) {
                    handlePeek();
                }
                else if (choice == 3) {
                    handleComplete();
                }
                else if (choice == 4) {
                    handleCompleted();
                }
                else if (choice == 5) {
                    handleCounts();
                }
            }
        }
        catch (NoSuchElementException e) {
            view.showMessage("");
            view.showMessage("Input ended.");
        }
        view.showMessage("Goodbye!");
    }


    /**
     * Converts typed text to a whole number within a range.
     *
     * @param text
     *            the text the user typed
     * @param min
     *            smallest allowed value
     * @param max
     *            largest allowed value
     * @return the number
     * @throws IllegalArgumentException
     *             if the text is not a whole number from min to max
     */
    public static int parseChoice(String text, int min, int max) {
        if (text != null) {
            try {
                int number = Integer.parseInt(text.trim());
                if (number >= min && number <= max) {
                    return number;
                }
            }
            catch (NumberFormatException e) {
                // falls through to the exception below
            }
        }
        throw new IllegalArgumentException(
            "Please enter a number from " + min + " to " + max + ".");
    }


    /**
     * Asks for each part of an order (re-asking any invalid part), builds the
     * DrinkOrder, and adds it to the queue.
     */
    public void handleAddOrder() {
        String name = askName();
        String drink = askDrink();
        String size = askSize();
        boolean priority = askPriority();

        DrinkOrder order = new DrinkOrder(name, drink, size, priority);
        queue.enqueue(order);
        view.showOrder("Added", order);
    }


    /**
     * Shows the next order without removing it.
     */
    public void handlePeek() {
        try {
            view.showOrder("Next order", queue.peek());
        }
        catch (EmptyQueueException e) {
            view.showMessage("No orders waiting.");
        }
    }


    /**
     * Completes the next order and shows it.
     */
    public void handleComplete() {
        try {
            view.showOrder("Completed", queue.dequeue());
        }
        catch (EmptyQueueException e) {
            view.showMessage("No orders waiting.");
        }
    }


    /**
     * Shows every completed order.
     */
    public void handleCompleted() {
        List<DrinkOrder> orders = queue.getCompleted();
        if (orders.isEmpty()) {
            view.showMessage("No completed orders yet.");
        }
        else {
            view.showOrderList("Completed orders:", orders);
        }
    }


    /**
     * Shows the waiting and completed counts.
     */
    public void handleCounts() {
        view.showCounts(queue.waitingCount(), queue.completedCount());
    }

    //~ Private Methods .......................................................

    private String askName() {
        while (true) {
            String name = view.prompt("Enter customer name: ");
            try {
                DrinkOrder.checkName(name);
                return name.trim();
            }
            catch (IllegalArgumentException e) {
                view.showError(e.getMessage());
            }
        }
    }


    private String askDrink() {
        List<String> drinks = DrinkOrder.DRINKS;
        while (true) {
            view.showDrinkMenu(drinks);
            String text = view.prompt("Drink number: ");
            try {
                return drinks.get(parseChoice(text, 1, drinks.size()) - 1);
            }
            catch (IllegalArgumentException e) {
                view.showError(e.getMessage());
            }
        }
    }


    private String askSize() {
        while (true) {
            String size =
                view.prompt("Enter size (S/M/L): ").trim().toUpperCase();
            try {
                DrinkOrder.checkSize(size);
                return size;
            }
            catch (IllegalArgumentException e) {
                view.showError(e.getMessage());
            }
        }
    }


    private boolean askPriority() {
        while (true) {
            String answer =
                view.prompt("Priority order? (y/n): ").trim().toLowerCase();
            if (answer.equals("y")) {
                return true;
            }
            if (answer.equals("n")) {
                return false;
            }
            view.showError("Please enter y or n.");
        }
    }
    
}
