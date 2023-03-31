package ra.payload.request;

import ra.model.entity.Image;

import java.util.ArrayList;

public class ProductRequest {
    private String productName;
    private String productDescription;
    private float productImportPrice;
    private String productImg;
    private int catalogId;
    private boolean productStatus;
//    private int[] colorStrArr = new int[50];
    private ArrayList<Integer> colorStrArr = new ArrayList<>();
//    private int[] sizeStrArr = new int[50];
    private ArrayList<Integer> sizeStrArr = new ArrayList<>();
//    private String[] listSubImage = new String[50];
    private ArrayList<String> listSubImage = new ArrayList<>();
//    private int[] proDetailArr = new int[50];
    private ArrayList<Integer> proDetailArr = new ArrayList<>();


    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
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

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public ArrayList<Integer> getColorStrArr() {
        return colorStrArr;
    }

    public void setColorStrArr(ArrayList<Integer> colorStrArr) {
        this.colorStrArr = colorStrArr;
    }

    public ArrayList<Integer> getSizeStrArr() {
        return sizeStrArr;
    }

    public void setSizeStrArr(ArrayList<Integer> sizeStrArr) {
        this.sizeStrArr = sizeStrArr;
    }

    public ArrayList<String> getListSubImage() {
        return listSubImage;
    }

    public void setListSubImage(ArrayList<String> listSubImage) {
        this.listSubImage = listSubImage;
    }

    public ArrayList<Integer> getProDetailArr() {
        return proDetailArr;
    }

    public void setProDetailArr(ArrayList<Integer> proDetailArr) {
        this.proDetailArr = proDetailArr;
    }
}
