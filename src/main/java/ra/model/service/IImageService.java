package ra.model.service;

import ra.model.entity.Image;

import java.util.List;
import java.util.Set;

public interface IImageService<T,V> extends IShopService<T,V>{
    Set<T> searchImageByProductId(V productId);
    Set<Image> findByImageLinkIn(String[] listSubImage);
    Image findByImageLink(String link);
}
