package ra.payload.respone;


public class ProductDetailResponse {
    private int productDetailId;
    private int quantity;
    private int productId;
    private String productName;
    private int colorId;
    private String colorName;
    private String colorHex;
    private int sizeId;
    private String sizeName;
    private boolean productDetailStatus;

    public ProductDetailResponse() {
    }

    public ProductDetailResponse(int productDetailId, int quantity, int productId, String productName, int colorId, String colorName, String colorHex, int sizeId, String sizeName, boolean productDetailStatus) {
        this.productDetailId = productDetailId;
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorHex = colorHex;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.productDetailStatus = productDetailStatus;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public boolean isProductDetailStatus() {
        return productDetailStatus;
    }

    public void setProductDetailStatus(boolean productDetailStatus) {
        this.productDetailStatus = productDetailStatus;
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
}
