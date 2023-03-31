package ra.payload.request;

public class ProductDetailRequest {
    private int quantity;
    private int productId;
    private int colorId;
    private int sizeId;
    private boolean proDetailStatus;

    public ProductDetailRequest() {
    }

    public ProductDetailRequest(int quantity, int productId, int colorId, int sizeId, boolean proDetailStatus) {
        this.quantity = quantity;
        this.productId = productId;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.proDetailStatus = proDetailStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public boolean isProDetailStatus() {
        return proDetailStatus;
    }

    public void setProDetailStatus(boolean proDetailStatus) {
        this.proDetailStatus = proDetailStatus;
    }
}
