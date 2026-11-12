
public class DrinkOrder {
    private String custName;
    private String drinkName;
    private String size;
    private boolean prior;

    public DrinkOrder(String cn, String dn, String s, boolean p) {
        custName = cn;
        drinkName = dn;
        size = s;
        prior = p;
    }


    public DrinkOrder() {
        custName = "Customer";
        drinkName = "Black Coffee";
        size = "M";
        prior = false;
    }
    
    public String getCustName() {
        return custName;
    }
    
    public String getDrinkName() {
        return drinkName;
    }
    
    public String getSize() {
        return size;
    }
    
    public boolean getPriority() {
        return prior;
    }

}
