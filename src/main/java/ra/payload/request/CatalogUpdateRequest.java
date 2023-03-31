package ra.payload.request;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CatalogUpdateRequest {
    private String catalogName;
    private String catalogDescription;
    private int catalogParentId;
    private LocalDateTime catalogCreateDate;
    private boolean catalogStatus;
//    private int[] strArr = new int[50];
    private ArrayList<Integer> strArr= new ArrayList<>();
//    private int[] strArrChild = new int[50];
    private ArrayList<Integer> strArrChild= new ArrayList<>();

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    public int getCatalogParentId() {
        return catalogParentId;
    }

    public void setCatalogParentId(int catalogParentId) {
        this.catalogParentId = catalogParentId;
    }

    public LocalDateTime getCatalogCreateDate() {
        return catalogCreateDate;
    }

    public void setCatalogCreateDate(LocalDateTime catalogCreateDate) {
        this.catalogCreateDate = catalogCreateDate;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public ArrayList<Integer> getStrArr() {
        return strArr;
    }

    public void setStrArr(ArrayList<Integer> strArr) {
        this.strArr = strArr;
    }

    public ArrayList<Integer> getStrArrChild() {
        return strArrChild;
    }

    public void setStrArrChild(ArrayList<Integer> strArrChild) {
        this.strArrChild = strArrChild;
    }
}
