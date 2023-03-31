package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.Size;
import ra.model.repository.SizeRepository;
import ra.model.service.ISizeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = SQLException.class)
public class SizeService implements ISizeService<Size,Integer> {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findById(Integer id) {
        return sizeRepository.findById(id).get();
    }

    @Override
    public Size saveOrUpdate(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void delete(Integer id) {
        Size sizeDelete =findById(id);
        sizeDelete.setSizeStatus(false);
        sizeRepository.save(sizeDelete);
    }

    @Override
    public List<Size> searchSize(String searchName) {
        return sizeRepository.searchSizeBySizeName(searchName);
    }

    @Override
    public List<Size> getSizeForUser() {
        return sizeRepository.getSizeForUser();
    }

    @Override
    public Set<Size> findBySizeIdIn(ArrayList<Integer> listSize) {
        return sizeRepository.findBySizeIdIn(listSize);
    }


}
