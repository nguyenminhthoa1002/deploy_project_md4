package ra.payload.respone;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ProductResponse {
    private int productId;
    private String productName;
    private String productDescription;
    private float productImportPrice;
    private float productExportPrice;
    private int totalQuantity;
    private String productImg;
    private String catalogName;
    private LocalDateTime productCreateDate;
    private boolean productStatus;
    private Set<String> listColorName;
    private Set<String> listSizeName;
    private Set<String> listSubImage;

    public ProductResponse() {
    }

    public ProductResponse(int productId, String productName, String productDescription, float productImportPrice, float productExportPrice, int totalQuantity, String productImg, String catalogName, LocalDateTime productCreateDate, boolean productStatus, Set<String> listColorName, Set<String> listSizeName, Set<String> listSubImage) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImportPrice = productImportPrice;
        this.productExportPrice = productExportPrice;
        this.totalQuantity = totalQuantity;
        this.productImg = productImg;
        this.catalogName = catalogName;
        this.productCreateDate = productCreateDate;
        this.productStatus = productStatus;
        this.listColorName = listColorName;
        this.listSizeName = listSizeName;
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductImportPrice() {
        return productImportPrice;
    }

    public void setProductImportPrice(float productImportPrice) {
        this.productImportPrice = productImportPrice;
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

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public LocalDateTime getProductCreateDate() {
        return productCreateDate;
    }

    public void setProductCreateDate(LocalDateTime productCreateDate) {
        this.productCreateDate = productCreateDate;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public Set<String> getListColorName() {
        return listColorName;
    }

    public void setListColorName(Set<String> listColorName) {
        this.listColorName = listColorName;
    }

    public Set<String> getListSizeName() {
        return listSizeName;
    }

    public void setListSizeName(Set<String> listSizeName) {
        this.listSizeName = listSizeName;
    }

    public Set<String> getListSubImage() {
        return listSubImage;
    }

    public void setListSubImage(Set<String> listSubImage) {
        this.listSubImage = listSubImage;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
