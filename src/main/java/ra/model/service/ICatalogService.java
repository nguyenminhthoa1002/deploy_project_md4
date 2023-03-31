package ra.model.service;

import org.springframework.data.repository.query.Param;
import ra.model.entity.Catalog;
import ra.payload.respone.CatalogResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ICatalogService<T,V> extends IShopService<T,V>{
    List<T> searchCatalog(String searchName);
    List<T> findChildById(int catId);
    List<T> getCatalogForCreateProduct();
    Set<Catalog> findByCatalogIdIn(ArrayList<Integer> listCatalog);
    List<Catalog> getCatalogForCreatCatalog();
    List<Catalog> findAllParentById(int catPaId);
    List<Catalog> findByCatalogParentId(int catalogId);

}
