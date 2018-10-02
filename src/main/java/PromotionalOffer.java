/**
 * Created by Behrooz on 21/09/2017.
 */
public class PromotionalOffer {

    private String promotedItemBarcode;
    private int promotedItemCount;
    private String discountedItemBarcode;
    private double discountPercentRate;

     public PromotionalOffer() {

     }

    public PromotionalOffer(String promotedItemBarcodeIn,
                            int promotedItemCountIn,
                            String discountedItemBarcodeIn,
                            double discountPercentRateIn) {
        promotedItemBarcode = promotedItemBarcodeIn;
        promotedItemCount = promotedItemCountIn;
        discountedItemBarcode = discountedItemBarcodeIn;
        discountPercentRate = discountPercentRateIn;

    }

    public String getPromotedItemBarcode() {
        return promotedItemBarcode;
    }

    public void setPromotedItemBarcode(String promotedItemBarcode) {
        this.promotedItemBarcode = promotedItemBarcode;
    }

    public int getPromotedItemCount() {
        return promotedItemCount;
    }

    public void setPromotedItemCount(int promotedItemCount) {
        this.promotedItemCount = promotedItemCount;
    }

    public String getDiscountedItemBarcode() {
        return discountedItemBarcode;
    }

    public void setDiscountedItemBarcode(String discountedItemBarcode) {
        this.discountedItemBarcode = discountedItemBarcode;
    }

    public double getDiscountPercentRate() {
        return discountPercentRate;
    }

    public void setDiscountPercentRate(double discountPercentRate) {
        this.discountPercentRate = discountPercentRate;
    }
}
