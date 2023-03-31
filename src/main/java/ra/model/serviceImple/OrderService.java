package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.Orders;
import ra.model.repository.OrderRepository;
import ra.model.service.IOrderService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(rollbackFor = SQLException.class)
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orders findById(Integer id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Orders saveOrUpdate(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }


    @Override
    public List<Orders> findOrdersByUsers_UserId(int userId) {
        return orderRepository.findOrdersByUsers_UserId(userId);
    }

    @Override
    public List<Orders> findByUsers_UserIdAndCreateDateBetween(int userId, LocalDate startFlashSale, LocalDate endFlashSale) {
        return orderRepository.findByUsers_UserIdAndCreateDateBetween(userId, startFlashSale, endFlashSale);
    }
}
