import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Behrooz on 20/09/2017.
 */
public class TestGroceryItem {

    @Test
    public void testParametrizedConstructor() {
        GroceryItem groceryItem = new GroceryItem("apple",   1,"APPL",   10);

        assertEquals("apple",groceryItem.getItemName());
        assert(1 == groceryItem.getPrice());
        assertEquals("APPL",groceryItem.getItemCode());
        assert(10 == groceryItem.getDiscountRate());
    }

    @Test
    public void testSetItemName(){
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setItemName("apple");
        assertEquals("apple",groceryItem.getItemName());
    }

    @Test
    public void testSetPrice(){
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setPrice(1);
        assert(1 == groceryItem.getPrice());
    }

    @Test
    public void testSetItemCode() {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setItemCode("APPL");
        assertEquals("APPL",groceryItem.getItemCode());
    }

    @Test
    public void testSetDiscountRate() {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setDiscountRate(10);
        assert(10 == groceryItem.getDiscountRate());
    }

}
