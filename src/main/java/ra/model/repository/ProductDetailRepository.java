package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.ProductDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer>, PagingAndSortingRepository<ProductDetail, Integer> {
    Set<ProductDetail> findByProduct_ProductId(int proId);

    Set<ProductDetail> findByColor_ColorNameOrSize_SizeName(String colorName, String sizeName);

    ProductDetail findByColor_ColorHexAndSize_SizeName(String colorHex, String sizeName);

    Set<ProductDetail> findByProductDetailIdIn(ArrayList<Integer> listProductDetailId);

    ProductDetail findByProduct_ProductIdAndColor_ColorIdAndSize_SizeId(int productId, int colorId, int sizeId);


}
