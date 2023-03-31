package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.Catalog;
import ra.model.service.ICatalogService;
import ra.model.repository.CatalogRepository;
import ra.payload.respone.CatalogResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = SQLException.class)
public class CatalogService implements ICatalogService<Catalog,Integer> {
    @Autowired
    private CatalogRepository catalogRepository;
    @Override
    public List<Catalog> searchCatalog(String searchName) {
        return catalogRepository.searchCatalogByCatalogNameContaining(searchName);
    }

    @Override
    public List<Catalog> findChildById(int catId) {
        return catalogRepository.findChildById(catId);
    }

    @Override
    public List<Catalog> getCatalogForCreateProduct() {
        return catalogRepository.getCatalogForCreateProduct();
    }

    @Override
    public Set<Catalog> findByCatalogIdIn(ArrayList<Integer> listCatalog) {
        return null;
    }

    @Override
    public List<Catalog> getCatalogForCreatCatalog() {
        return catalogRepository.getCatalogForCreatCatalog();
    }

    @Override
    public List<Catalog> findAllParentById(int catPaId) {
        return catalogRepository.findAllParentById(catPaId);
    }

    @Override
    public List<Catalog> findByCatalogParentId(int catalogId) {
        return catalogRepository.findByCatalogParentId(catalogId);
    }


    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Catalog findById(Integer id) {
        return catalogRepository.findById(id).get();
    }

    @Override
    public Catalog saveOrUpdate(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public void delete(Integer id) {
        Catalog catDelete = findById(id);
        catDelete.setCatalogStatus(false);
        catalogRepository.save(catDelete);
    }

}
