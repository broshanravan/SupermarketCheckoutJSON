/**
 * Created by Behrooz on 19/09/2017.
 */
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class TestPriceCalculator {

    PriceCalculator priceCalculatur = new PriceCalculator();

    GroceryItem apple = null;
    GroceryItem milk = null;
    GroceryItem soup = null;
    GroceryItem bread = null;

    @Before
    public void initiateGroceryItems(){
         apple = new GroceryItem("apple",1.0,"APL",10);
         milk = new GroceryItem("milk",1.3, "MLK",0);
         soup = new GroceryItem("soup",0.65, "SUP",0);
         bread = new GroceryItem("bread",0.8, "BRD",0);
    }

    @Test
    public void testCodeIsValid(){
        assertTrue(priceCalculatur.isBarCodeValid("APL"));
        assertFalse(priceCalculatur.isBarCodeValid("INVALID"));
    }

    /**
     * in case number of soup cans discount units are
     * less that the number of breads
     * Then all the soup can discounts will apply
     */
    @Test
    public void testWholeDiscount() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();
        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("SUP", 2, "BRD",50);

        promotionalOfferList.add(promotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);

        groceryItemList.add(apple);
        groceryItemList.add(soup);
        groceryItemList.add(milk);
        groceryItemList.add(soup);
        groceryItemList.add(soup);
        groceryItemList.add(soup);
        groceryItemList.add(soup);
        groceryItemList.add(soup);
        groceryItemList.add(bread);
        groceryItemList.add(bread);
        groceryItemList.add(bread);

        double discount = priceCalculator.calculateTotalDiscount(groceryItemList);
        assertEquals(1.3, discount );

    }

    /**
     * in case number of promoted discount goods are
     * more than the number of discounted
     * only as many discounts as
     * the number of discounted will apply
     */
    @Test
    public void testPartialDiscount() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();
        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("SUP", 2, "BRD",50);

        promotionalOfferList.add(promotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);


        groceryItemList.add(apple);
        groceryItemList.add(soup);
        groceryItemList.add(milk);
        groceryItemList.add(soup);
        groceryItemList.add(soup);
        groceryItemList.add(soup);
        groceryItemList.add(soup);
        groceryItemList.add(soup);
        groceryItemList.add(bread);
        groceryItemList.add(bread);

        double discount = priceCalculator.calculateTotalDiscount(groceryItemList);
        assertEquals(0.9, discount );

    }

    /**
     * in case number the items in
     * The basket do not match amy os
     * The discound offers
     */
    @Test
    public void testNoDiscountsApply() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();

        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("SUP", 2, "BRD",50);

        promotionalOfferList.add(promotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);

        groceryItemList.add(soup);
        groceryItemList.add(milk);
        groceryItemList.add(bread);
        groceryItemList.add(bread);

        double discount = priceCalculator.calculateTotalDiscount(groceryItemList);
        assertEquals(0.0, discount );

    }

}
