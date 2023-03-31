package ra.payload.respone;

import java.util.Set;

public class ProductResponseForUser {
    private int productId;
    private String productName;
    private float productExportPrice;
    private String productImg;
    private Set<String> listSubImage;
    public ProductResponseForUser() {
    }

    public ProductResponseForUser(int productId, String productName, float productExportPrice, String productImg, Set<String> listSubImage) {
        this.productId = productId;
        this.productName = productName;
        this.productExportPrice = productExportPrice;
        this.productImg = productImg;
        this.listSubImage = listSubImage;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductExportPrice() {
        return productExportPrice;
    }

    public void setProductExportPrice(float productExportPrice) {
        this.productExportPrice = productExportPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Set<String> getListSubImage() {
        return listSubImage;
    }

    public void setListSubImage(Set<String> listSubImage) {
        this.listSubImage = listSubImage;
    }
}
