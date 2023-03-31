package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.model.entity.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public interface ColorRepository extends JpaRepository<Color,Integer> {
    List<Color> searchColorByColorName(String searchName);

    @Query(value = "select * from color where colorStatus = 1",nativeQuery = true)
    List<Color> getColorForUser();
    Set<Color> findByColorIdIn(ArrayList<Integer> listColor);
}
