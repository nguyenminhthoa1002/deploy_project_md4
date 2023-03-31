package ra.model.service;

import ra.model.entity.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ISizeService<T,V> extends IShopService<T,V>{
    List<T> searchSize(String searchName);
    List<T> getSizeForUser();
    Set<Size> findBySizeIdIn(ArrayList<Integer> listSize);
}
