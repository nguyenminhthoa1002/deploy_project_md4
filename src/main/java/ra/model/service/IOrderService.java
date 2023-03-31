package ra.model.service;

import ra.model.entity.Orders;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService extends IShopService<Orders,Integer> {
    List<Orders> findOrdersByUsers_UserId(int userId);
    List<Orders> findByUsers_UserIdAndCreateDateBetween(int userId, LocalDate startFlashSale, LocalDate endFlashSale);
}
