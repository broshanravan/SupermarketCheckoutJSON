/**
 * Created by Behrooz on 19/09/2017.
 */
import java.text.DecimalFormat;
import java.util.*;

public class PriceCalculator  implements IPriceCalculator{

    Inventory inventory = new Inventory();
    DecimalFormat decimalFormatter = new DecimalFormat("0.00");

    Map<String, GroceryItem> groceryItemsMap = inventory.readItemsFromFile();


    /**
     * Calculating the total price.
     * This function runs continuously until the
     * user completes the shopping
     * @param itemCode
     * @return finalPayment
     */

    public String calculateTotalPayment(String itemCode) {

        String finalPayment = "0";
        double subTotal = 0;
        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        while (!"end".equalsIgnoreCase(itemCode)) {
            GroceryItem groceryItem = groceryItemsMap.get(itemCode);
            if(groceryItem != null){
                basket.add(groceryItem);
                subTotal += groceryItem.getPrice();

                System.out.println("Decribtion : " +  groceryItem.getItemName());
                System.out.println("Price : \u00a3" +  decimalFormatter.format(groceryItem.getPrice()));
                System.out.println("Sub-total : \u00a3" + decimalFormatter.format(subTotal));

                System.out.println("please enter next Item code; " );
            }  else {
                System.out.println("Invalid barCode, Please try again");
            }
            Scanner scanner = new Scanner(System.in);
            itemCode = scanner.nextLine();
        }
        subTotal = Double.valueOf(decimalFormatter.format(subTotal));

        System.out.println("Sub-total is: \u00a3" + subTotal);
        double discount = calculateTotalDiscount(basket);

        if (discount == 0) {
            System.out.println("(No offers available)");
        } else {
            System.out.println("total discount amount is: \u00a3" + decimalFormatter.format(discount));
        }
        finalPayment = decimalFormatter.format(subTotal - discount);
        return  finalPayment;

    }

    /**
     * This function calculates the total
     * discount for all the purchased items
     * @param shoppingBasket
     * @return discount
     */
    public double calculateTotalDiscount(List<GroceryItem> shoppingBasket) {
        double discount = 0;
        for(GroceryItem groceryItem :shoppingBasket) {

            double discountRate = groceryItem.getDiscountRate();
            if (discountRate != 0) {
                double price = groceryItem.getPrice();
                discount =+ price * discountRate/100;

            }

        }

        double promotionalDiscount = getPromotionalldiscount(shoppingBasket);
        discount += promotionalDiscount;



        discount = Double.parseDouble(decimalFormatter.format(discount));
        return discount;
    }

    /**
     * will validate the barcode entered by
     * the user
     * @param barCodeIn
     * @return validation result
     */
    public boolean isBarCodeValid(String barCodeIn){

        List<String> barCodeList = inventory.getAllBarCodes();
        if(barCodeList.contains(barCodeIn)) {
            return true;
        }

        return false;
    }

    /**
     * This unction calculates the combined
     * discount for promoted goods
     * @param shoppingBasket
     * @return promotionalDiscount
     */
    public double getPromotionalldiscount(List<GroceryItem> shoppingBasket){
        double promotionalDiscount = 0;
        List<PromotionalOffer> promotionalOfferList = inventory.getPromotionalOffersList();

        for(PromotionalOffer promotionalOffer :promotionalOfferList ) {
            int promotedGoodCount = 0 ;
            int discountedGoodCount = 0;
            for (GroceryItem groceryItem : shoppingBasket) {
                String itemCode = groceryItem.getItemCode();
                if (itemCode.equalsIgnoreCase(promotionalOffer.getDiscountedItemBarcode())){
                    discountedGoodCount ++;
                } else if (itemCode.equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode().trim())) {
                    promotedGoodCount ++;
                }
            }
            int promotedItemsOfferCount = promotionalOffer.getPromotedItemCount();
            if (discountedGoodCount != 0 && promotedGoodCount >= promotedItemsOfferCount) {
                int numberOfDiscountedGood = promotedGoodCount/promotedItemsOfferCount;

                double discountedGoodPrice = inventory.getItemPrice(promotionalOffer.getPromotedItemBarcode());
                double discountRate = promotionalOffer.getDiscountPercentRate();

                if(discountedGoodCount >= numberOfDiscountedGood) {
                    promotionalDiscount += discountedGoodCount * discountedGoodPrice * (discountRate/100);
                } else {
                    promotionalDiscount += promotedItemsOfferCount * discountedGoodPrice * (discountRate/100);
                }
            }

        }

        return promotionalDiscount;
    }


    public Map<String, GroceryItem> getGroceryItemsMap() {
        return groceryItemsMap;
    }

    public void setGroceryItemsMap(Map<String, GroceryItem> groceryItemsMap) {
        this.groceryItemsMap = groceryItemsMap;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
