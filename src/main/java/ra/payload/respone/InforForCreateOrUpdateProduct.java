package ra.payload.respone;

import ra.model.entity.Catalog;
import ra.model.entity.Color;
import ra.model.entity.Size;

import java.util.List;
import java.util.Set;

public class InforForCreateOrUpdateProduct {
    private List<CatalogResponse> listCatalog;
    private List<ColorResponse> listColor;
    private List<SizeResponse> listSize;

    public InforForCreateOrUpdateProduct() {
    }

    public InforForCreateOrUpdateProduct(List<CatalogResponse> listCatalog, List<ColorResponse> listColor, List<SizeResponse> listSize) {
        this.listCatalog = listCatalog;
        this.listColor = listColor;
        this.listSize = listSize;
    }

    public List<CatalogResponse> getListCatalog() {
        return listCatalog;
    }

    public void setListCatalog(List<CatalogResponse> listCatalog) {
        this.listCatalog = listCatalog;
    }

    public List<ColorResponse> getListColor() {
        return listColor;
    }

    public void setListColor(List<ColorResponse> listColor) {
        this.listColor = listColor;
    }

    public List<SizeResponse> getListSize() {
        return listSize;
    }

    public void setListSize(List<SizeResponse> listSize) {
        this.listSize = listSize;
    }
}
