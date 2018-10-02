
/**
 * Created by Behrooz on 20/09/2017.
 */
public class GroceryItem {
    private String itemName;
    private double price;
    private String itemCode;
    private double discountRate;


    public GroceryItem(String itemNameIn,
                 double priceIn,
                 String itemCodeIn,
                 double discountRateIn) {

         itemName = itemNameIn;
         price = priceIn;
         itemCode = itemCodeIn;
         discountRate = discountRateIn;

    }

    public GroceryItem() {

    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
