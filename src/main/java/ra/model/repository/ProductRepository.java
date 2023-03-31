package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.Color;
import ra.model.entity.Product;
import ra.model.entity.Size;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> searchProductByProductNameContaining(String searchName);

    List<Product> searchProductByProductExportPriceBetween(float min, float max);

    Set<Product> findByProductStatusIsTrue();

    Set<Product> findByListColorIn(Set<Color> listColor);

    Set<Product> findByListSizeIn(Set<Size> listSize);

    Set<Product> findByListColorInAndListSizeInAndProductExportPriceBetween(Set<Color> listColor, Set<Size> listSize, float min, float max);

    Set<Product> findByCatalog_CatalogId(int catId);

}
