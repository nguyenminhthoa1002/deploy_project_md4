package ra.payload.request;

import java.time.LocalDateTime;

public class CatalogRequest {
    private String catalogName;
    private String catalogDescription;
    private int catalogParentId;
    private LocalDateTime catalogCreateDate;
    private boolean catalogStatus;


    public CatalogRequest(String catalogName, String catalogDescription, int catalogParentId, LocalDateTime catalogCreateDate, boolean catalogStatus) {
        this.catalogName = catalogName;
        this.catalogDescription = catalogDescription;
        this.catalogParentId = catalogParentId;
        this.catalogCreateDate = catalogCreateDate;
        this.catalogStatus = catalogStatus;
    }

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
}
