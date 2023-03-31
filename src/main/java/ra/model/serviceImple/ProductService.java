package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.Color;
import ra.model.entity.Size;
import ra.model.repository.ProductRepository;
import ra.model.service.IProductService;
import ra.model.entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = SQLException.class)
public class ProductService implements IProductService<Product,Integer> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> searchProduct(String searchName) {
        return productRepository.searchProductByProductNameContaining(searchName);
    }

    @Override
    public List<Product> searchProductByProductExportPriceBetween(float min, float max) {
        return productRepository.searchProductByProductExportPriceBetween(min, max);
    }

    @Override
    public Set<Product> findByListColorIn(Set<Color> listColor) {
        return productRepository.findByListColorIn(listColor);
    }

    @Override
    public Set<Product> findByListSizeIn(Set<Size> listSize) {
        return productRepository.findByListSizeIn(listSize);
    }

    @Override
    public Set<Product> findByListColorInAndListSizeInAndProductExportPriceBetween(Set<Color> listColor, Set<Size> listSize, float min, float max) {
        return productRepository.findByListColorInAndListSizeInAndProductExportPriceBetween(listColor,listSize,min,max);
    }

    @Override
    public Set<Product> findByCatalog_CatalogId(int catId) {
        return productRepository.findByCatalog_CatalogId(catId);
    }

    @Override
    public Set<Product> findByProductStatusIsTrue() {
        return productRepository.findByProductStatusIsTrue();
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        Product proDelete = findById(id);
        proDelete.setProductStatus(false);
        productRepository.save(proDelete);
    }
}
