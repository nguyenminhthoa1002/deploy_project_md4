package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.model.entity.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public interface SizeRepository extends JpaRepository<Size,Integer> {
    List<Size> searchSizeBySizeName(String searchName);

    @Query(value = "select * from size where sizeStatus = 1",nativeQuery = true)
    List<Size> getSizeForUser();
    Set<Size> findBySizeIdIn(ArrayList<Integer> listSize);


}
