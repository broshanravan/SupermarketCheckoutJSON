
/**
 * Created by Behrooz on 19/09/2017.
 */

import java.util.List;

public interface IPriceCalculator {

    public String calculateTotalPayment(String itemCode) ;

    public double calculateTotalDiscount(List<GroceryItem> groceryItemsList);

    public boolean isBarCodeValid(String barCodeIn);
}
