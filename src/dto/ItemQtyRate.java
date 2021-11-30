package dto;

public class ItemQtyRate {
    private String itemCode;
    private int itemQty;

    public ItemQtyRate(String itemCode, int itemQty) {
        this.itemCode = itemCode;
        this.itemQty = itemQty;
    }

    public ItemQtyRate() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }
}
