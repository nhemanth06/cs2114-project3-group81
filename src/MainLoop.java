
import java.util.Scanner;
import java.util.List;
import java.util.NoSuchElementException;

public class MainLoop
{
    private static final String NO_ORDERS = "No orders waiting.";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        OrderQueue queue = new OrderQueue();
        boolean exit = false;

        try {
            while (!exit) {
                showMenu();
                int choice = readChoice(scan);
                if (choice == 0) {
                    exit = true;
                }
                else if (choice == 1) {
                    addOrder(scan, queue);
                }
                else if (choice == 2) {
                    viewNext(queue);
                }
                else if (choice == 3) {
                    completeNext(queue);
                }
                else if (choice == 4) {
                    viewCompleted(queue);
                }
                else if (choice == 5) {
                    viewCounts(queue);
                }
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("\nInput ended.");
        }
        System.out.println("Goodbye!");
    }


    // ---- Show user menu ----


    private static void showMenu()
    {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("  1 - Add an order");
        System.out.println("  2 - See the next order");
        System.out.println("  3 - Complete the next order");
        System.out.println("  4 - See completed orders");
        System.out.println("  5 - See waiting/completed counts");
        System.out.println("  0 - Exit");
        System.out.print("Choice: ");
    }
    
    /**
     * Reads a menu command.
     *
     * @return 0 to 5, or -1 (after printing an error) if the input was not a
     *         number in that range
     */
    private static int readChoice(Scanner scan) {
        String text = scan.nextLine().trim();
        try {
            int choice = Integer.parseInt(text);
            if (choice >= 0 && choice <= 5) {
                return choice;
            }
        }
        catch (NumberFormatException e) {
            // falls through to the error message
        }
        System.out.println("Error: please enter a number from 0 to 5.");
        return -1;
    }
    
    // ----- Bad Input mitigation -----
 // ---- prompts: each repeats until it gets a valid answer ----

    private static String promptName(Scanner scan) {
        while (true) {
            System.out.print("Enter customer name: ");
            String name = scan.nextLine();
            try {
                DrinkOrder.checkName(name);
                return name.trim();
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private static String promptDrink(Scanner scan) {
        List<String> drinks = DrinkOrder.DRINKS;
        while (true) {
            System.out.println("Choose a drink:");
            for (int i = 0; i < drinks.size(); i++) {
                System.out.println("  " + (i + 1) + " - " + drinks.get(i));
            }
            System.out.print("Drink number: ");
            String text = scan.nextLine().trim();
            try {
                int number = Integer.parseInt(text);
                if (number >= 1 && number <= drinks.size()) {
                    return drinks.get(number - 1);
                }
            }
            catch (NumberFormatException e) {
                // falls through to the error message
            }
            System.out.println("Error: please enter a number from 1 to "
                + drinks.size() + ".");
        }
    }


    private static String promptSize(Scanner scan) {
        while (true) {
            System.out.print("Enter size (S/M/L): ");
            String size = scan.nextLine().trim().toUpperCase();
            try {
                DrinkOrder.checkSize(size);
                return size;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private static boolean promptPriority(Scanner scan) {
        while (true) {
            System.out.print("Priority order? (y/n): ");
            String answer = scan.nextLine().trim().toLowerCase();
            if (answer.equals("y")) {
                return true;
            }
            if (answer.equals("n")) {
                return false;
            }
            System.out.println("Error: please enter y or n.");
        }
    }


    
    
    // ----- Start of commands -----
    private static void addOrder(Scanner scan, OrderQueue queue) {
        String name = promptName(scan);
        String drink = promptDrink(scan);
        String size = promptSize(scan);
        boolean priority = promptPriority(scan);

        DrinkOrder order = new DrinkOrder(name, drink, size, priority);
        queue.enqueue(order);
        System.out.println("Added: " + order);
    }


    private static void viewNext(OrderQueue queue) {
        try {
            System.out.println("Next order: " + queue.peek());
        }
        catch (EmptyQueueException e) {
            System.out.println(NO_ORDERS);
        }
    }


    private static void completeNext(OrderQueue queue)
         {
        try {
            DrinkOrder done = queue.dequeue();
            System.out.println("Completed: " + done);
        }
        catch (EmptyQueueException e) {
            System.out.println(NO_ORDERS);
        }
    }


    private static void viewCompleted(OrderQueue queue) {
        List<DrinkOrder> orders = queue.getCompleted();
        if (orders.isEmpty()) {
            System.out.println("No completed orders yet.");
            return;
        }
        System.out.println("Completed orders:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + orders.get(i));
        }
    }


    private static void viewCounts(OrderQueue queue) {
        System.out.println("Waiting: " + queue.waitingCount());
        System.out.println("Completed: " + queue.completedCount());
    }
}
