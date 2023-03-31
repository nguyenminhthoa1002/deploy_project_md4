package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    OrderDetail findByProductDetail_ProductDetailIdAndOrders_OrderId(int productDetailId, int orderId);
    List<OrderDetail> findByOrders_OrderId(int orderId);
    List<OrderDetail> findByOrdersIn(List<Orders> listOrder);
}
