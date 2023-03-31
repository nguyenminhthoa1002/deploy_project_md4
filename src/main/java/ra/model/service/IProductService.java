package ra.model.service;

import ra.model.entity.Color;
import ra.model.entity.Product;
import ra.model.entity.Size;

import java.util.List;
import java.util.Set;

public interface IProductService<T,V> extends IShopService<T,V> {
    List<T> searchProduct(String searchName);
    List<T> searchProductByProductExportPriceBetween(float min, float max);
    Set<Product> findByListColorIn(Set<Color> listColor);
    Set<Product> findByListSizeIn(Set<Size> listSize);
    Set<Product> findByListColorInAndListSizeInAndProductExportPriceBetween(Set<Color> listColor,Set<Size> listSize,float min,float max);
    Set<Product> findByCatalog_CatalogId(int catId);
    Set<Product> findByProductStatusIsTrue();
}
