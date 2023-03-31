package ra.model.service;

import ra.model.entity.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface IColorService<T,V> extends IShopService<T,V>{
    List<T> searchColor(String searchName);
    List<T> getColorForUser();
    Set<Color> findByColorIdIn(ArrayList<Integer> listColor);
}
