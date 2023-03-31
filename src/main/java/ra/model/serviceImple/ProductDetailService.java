package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.ProductDetail;
import ra.model.repository.ProductDetailRepository;
import ra.model.service.IProductDetailService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = SQLException.class)
public class ProductDetailService implements IProductDetailService<ProductDetail,Integer> {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail findById(Integer id) {
        return productDetailRepository.findById(id).get();
    }

    @Override
    public ProductDetail saveOrUpdate(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public void delete(Integer id) {
        ProductDetail pdDelate = findById(id);
        pdDelate.setProductDetailStatus(false);
        productDetailRepository.save(pdDelate);
    }


    @Override
    public Set<ProductDetail> findByProduct_ProductId(int proId) {
        return productDetailRepository.findByProduct_ProductId(proId);
    }

    @Override
    public Set<ProductDetail> findByColor_ColorNameOrSize_SizeName(String colorName, String sizeName) {
        return productDetailRepository.findByColor_ColorNameOrSize_SizeName(colorName,sizeName);
    }

    @Override
    public ProductDetail findByColor_ColorHexAndSize_SizeName(String colorHex, String sizeName) {
        return productDetailRepository.findByColor_ColorHexAndSize_SizeName(colorHex,sizeName);
    }

    @Override
    public Set<ProductDetail> findByProductDetailIdIn(ArrayList<Integer> listProductDetailId) {
        return productDetailRepository.findByProductDetailIdIn(listProductDetailId);
    }


    @Override
    public ProductDetail findByProduct_ProductIdAndColor_ColorIdAndSize_SizeId(int productId, int colorId, int sizeId) {
        return productDetailRepository.findByProduct_ProductIdAndColor_ColorIdAndSize_SizeId(productId,colorId,sizeId);
    }
}
