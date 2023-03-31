package ra.payload.respone;

import java.util.Set;

public class InforForProductDetailPage {
    private String productName;
    private String productDescription;
    private float productExportPrice;
    private Set<String> listColorHex;
    private Set<String> listSizeName;
    private String productImg;
    private Set<String> listSubImage;

    public InforForProductDetailPage() {
    }

    public InforForProductDetailPage(int productDetailId, String productName, String productDescription, float productExportPrice, int quantity, Set<String> listColorHex, Set<String> listSizeName, String productImg, Set<String> listSubImage) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productExportPrice = productExportPrice;
        this.listColorHex = listColorHex;
        this.listSizeName = listSizeName;
        this.productImg = productImg;
        this.listSubImage = listSubImage;
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

    public float getProductExportPrice() {
        return productExportPrice;
    }

    public void setProductExportPrice(float productExportPrice) {
        this.productExportPrice = productExportPrice;
    }


    public Set<String> getListColorHex() {
        return listColorHex;
    }

    public void setListColorHex(Set<String> listColorHex) {
        this.listColorHex = listColorHex;
    }

    public Set<String> getListSizeName() {
        return listSizeName;
    }

    public void setListSizeName(Set<String> listSizeName) {
        this.listSizeName = listSizeName;
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
