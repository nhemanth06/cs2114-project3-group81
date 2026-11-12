
import java.util.Scanner;

public class MainLoop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        Deque<DrinkOrder> line = new Deque<DrinkOrder>();
        while (!exit) {
            System.out.println(
                "What would you like to do? Press 1 to add an order, 2 to see the next order, and 0 to exit.");
            int input = scan.nextInt();
            if (input==0) {
                exit = true;
            }
            if (input==1) {
                System.out.println("Enter name: ");
                String cname = scan.next();
                System.out.println("Enter drink name: ");
                String dname = scan.next();
                System.out.println("Enter size: ");
                String sz = scan.next();
                System.out.println("Enter priority(y/n): ");
                String prior = scan.next();
                boolean priorBool = false;
                if (prior.equals("y")) {
                    priorBool = true;
                }
                DrinkOrder curr = new DrinkOrder(cname, dname, sz, priorBool);
                line.addToBack(curr);
            }
            if (input==2) {
                DrinkOrder out = line.removeFront();
                System.out.println("Name: " + out.getCustName());
                System.out.println("Drink: " + out.getDrinkName());
                System.out.println("Size: " + out.getSize());
                if (out.getPriority()) {
                    System.out.println("Priority: Yes");
                } else {
                    System.out.println("Priority: No");
                }
            }
        }
    }
}
