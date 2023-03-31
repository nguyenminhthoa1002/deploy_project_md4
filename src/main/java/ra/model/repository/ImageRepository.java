package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.Image;

import java.util.List;
import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    @Query(value = "select * from image where productId=:proId",nativeQuery = true)
    Set<Image> searchImageByProductId(@Param("proId") int proId);

    Set<Image> findByImageLinkIn(String[] listSubImage);

    Image findByImageLink(String link);
}
