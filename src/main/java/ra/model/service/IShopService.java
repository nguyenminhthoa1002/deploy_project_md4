package ra.model.service;

import java.util.List;

public interface IShopService<T,V> {
    List<T> findAll();
    T findById(V id);
    T saveOrUpdate(T t);
    void delete(V id);
}
