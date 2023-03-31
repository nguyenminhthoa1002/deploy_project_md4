package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Orders;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findOrdersByUsers_UserId(int userId);
    List<Orders> findByUsers_UserIdAndCreateDateBetween(int userId, LocalDate startFlashSale, LocalDate endFlashSale);
}
