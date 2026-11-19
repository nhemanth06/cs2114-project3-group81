
import java.util.Scanner;
import java.util.List;
import java.util.NoSuchElementException;

public class MainLoop
{
    private static final String NO_ORDERS = "No orders waiting.";

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        Deque<DrinkOrder> completed = new Deque<DrinkOrder>();
        Deque<DrinkOrder> line = new Deque<DrinkOrder>();

        try
        {
            while (!exit)
            {
                showMenu();
                int choice = readChoice(scan);
                if (choice == 0)
                {
                    exit = true;
                }
                else if (choice == 1)
                {
                    addOrder(scan, line);
                }
                else if (choice == 2)
                {
                    viewNext(line);
                }
                else if (choice == 3)
                {
                    completeNext(line, completed);
                }
                else if (choice == 4)
                {
                    viewCompleted(completed);
                }
                else if (choice == 5)
                {
                    viewCounts(line, completed);
                }
            }
        }
        catch (NoSuchElementException e)
        {
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

}
