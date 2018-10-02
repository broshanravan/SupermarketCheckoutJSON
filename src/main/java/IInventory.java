
/**
 * Created by Behrooz on 21/09/2017.
 */
import java.util.*;

public interface IInventory {
    public Map<String,GroceryItem> readItemsFromFile();

    public List getAllBarCodes();

    public List<PromotionalOffer> getPromotionalOffersList();
}
