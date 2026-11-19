import java.util.List;

public class DrinkOrder
{

    private String custName;
    private String drinkName;
    private String size;
    private boolean prior;

    public static final List<String> SIZES = List.of("S", "M", "L");

    public static final List<String> DRINKS = List.of(
        "Black Coffee",
        "Latte",
        "Cappucino",
        "Americano",
        "Espresso",
        "Mocha");

    public static final int MAX_NAME_LENGTH = 50;

    public DrinkOrder(String cn, String dn, String s, boolean p)
    {
        checkName(cn);
        checkDrink(dn);
        checkSize(s);
        custName = cn.trim();
        drinkName = dn;
        size = s;
        prior = p;
    }


    public DrinkOrder()
    {
        this("Customer", "Black Coffee", "M", false);
    }


    public static void checkName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "Customer name cannot be blank.");
        }
        if (name.trim().length() > MAX_NAME_LENGTH)
        {
            throw new IllegalArgumentException(
                "Customer name cannot be longer than " + MAX_NAME_LENGTH
                    + " characters.");
        }
    }


    /**
     * Checks a drink name against the menu.
     *
     * @param drink
     *            the drink to check
     * @throws IllegalArgumentException
     *             if the drink is not on the menu
     */
    public static void checkDrink(String drink)
    {
        if (drink == null || !DRINKS.contains(drink))
        {
            throw new IllegalArgumentException(
                "That drink is not on the menu.");
        }
    }


    public static void checkSize(String size)
    {
        if (size == null || !SIZES.contains(size))
        {
            throw new IllegalArgumentException("Size must be S, M, or L.");
        }
    }


    public String getCustName()
    {
        return custName;
    }


    public String getDrinkName()
    {
        return drinkName;
    }


    public String getSize()
    {
        return size;
    }


    public boolean getPriority()
    {
        return prior;
    }


    /**
     * One-line description, e.g. "Ana - M Latte (PRIORITY)".
     */
    @Override
    public String toString()
    {
        String text = custName + " - " + size + " " + drinkName;
        if (prior)
        {
            text += " (PRIORITY)";
        }
        return text;
    }
}
