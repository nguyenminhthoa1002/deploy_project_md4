package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.Image;
import ra.model.repository.ImageRepository;
import ra.model.service.IImageService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = SQLException.class)
public class ImageService implements IImageService<Image,Integer> {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Integer id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public Image saveOrUpdate(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void delete(Integer id) {
        Image imageDelete = findById(id);
        imageDelete.setImageStatus(false);
        imageRepository.save(imageDelete);
    }

    @Override
    public Set<Image> searchImageByProductId(Integer productId) {
        return imageRepository.searchImageByProductId(productId);
    }

    @Override
    public Set<Image> findByImageLinkIn(String[] listSubImage) {
        return imageRepository.findByImageLinkIn(listSubImage);
    }

    @Override
    public Image findByImageLink(String link) {
        return imageRepository.findByImageLink(link);
    }

}
