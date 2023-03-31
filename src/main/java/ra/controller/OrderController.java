package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.*;
import ra.model.service.*;
import ra.payload.request.Checkout;
import ra.payload.request.OrderDetailRequest;
import ra.security.CustomUserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private IProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<?> addToCart(@RequestBody OrderDetailRequest orderDetailRequest, @RequestParam("action") String action) {
        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = userService.findUsersByUserName(usersChangePass.getUsername());
        Product product = (Product) productService.findById(orderDetailRequest.getProductId());
        List<Orders> ordersList = orderService.findOrdersByUsers_UserId(users.getUserId());
        ProductDetail productDetail = productDetailService.findByColor_ColorHexAndSize_SizeName(orderDetailRequest.getColorHex(), orderDetailRequest.getSizeName());

        for (Orders orders : ordersList) {
            OrderDetail orderDetail = orderDetailService.findByProductDetail_ProductDetailIdAndOrders_OrderId(productDetail.getProductDetailId(), orders.getOrderId());
            if (orders.getOrderStatus() == 0) {
                try {
                    if (orderDetail != null) {
                        if (action.equals("create")) {
                            orderDetail.setQuantity(orderDetail.getQuantity() + orderDetailRequest.getQuantity());
                            orderDetail.setTotalMoneyOrderDetail(product.getProductExportPrice() * orderDetail.getQuantity());
                            orderDetailService.saveOrUpdate(orderDetail);
                        } else if (action.equals("edit")) {
                            orderDetail.setQuantity(orderDetailRequest.getQuantity());
                            orderDetail.setTotalMoneyOrderDetail(product.getProductExportPrice() * orderDetail.getQuantity());
                            orderDetailService.saveOrUpdate(orderDetail);
                        }
                    } else {
                        OrderDetail newOrderDetail = new OrderDetail();
                        newOrderDetail.setProductDetail(productDetail);
                        newOrderDetail.setOrders(orders);
                        newOrderDetail.setQuantity(orderDetailRequest.getQuantity());
                        newOrderDetail.setPrice(product.getProductExportPrice());
                        newOrderDetail.setTotalMoneyOrderDetail(newOrderDetail.getPrice() * newOrderDetail.getQuantity());
                        orderDetailService.saveOrUpdate(newOrderDetail);
                    }

                    float totalMoney = 0;
                    for (OrderDetail od : orders.getListOrderDetail()) {
                        totalMoney += od.getTotalMoneyOrderDetail();
                        orders.setTotalMoney(totalMoney);
                    }
                    orderService.saveOrUpdate(orders);

                    return ResponseEntity.ok().body("Add to cart success!");
                } catch (Exception ex) {
                    return ResponseEntity.badRequest().body("Add to cart failed!");
                }
            }
        }
        return null;
    }

    @DeleteMapping("/{orderDetailId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable("orderDetailId") int orderDetailId) {
        try {
            orderDetailService.delete(orderDetailId);
            CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users users = userService.findUsersByUserName(usersChangePass.getUsername());
            List<Orders> ordersList = orderService.findOrdersByUsers_UserId(users.getUserId());
            for (Orders orders : ordersList) {
                if (orders.getOrderStatus() == 0) {
                    List<OrderDetail> orderDetailList = orderDetailService.findByOrders_OrderId(orders.getOrderId());

                    float totalMoney = 0;
                    for (OrderDetail od : orderDetailList) {
                        totalMoney += od.getTotalMoneyOrderDetail();
                        orders.setTotalMoney(totalMoney);
                    }
                    orderService.saveOrUpdate(orders);
                    return ResponseEntity.ok().body("Delete success!");
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Delete failed!");
        }
        return null;
    }

    @PutMapping("checkout")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<?> checkout(@RequestBody Checkout checkout) {
        try {
            CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users users = userService.findUsersByUserName(usersChangePass.getUsername());
            List<Orders> ordersList = orderService.findOrdersByUsers_UserId(users.getUserId());
            for (Orders orders : ordersList) {
                if (orders.getOrderStatus() == 0) {
                    orders.setNote(checkout.getNote());
                    LocalDateTime time = LocalDateTime.now();
                    orders.setCreateDate(time);
                    orders.setOrderStatus(1);
                    orderService.saveOrUpdate(orders);

                    List<OrderDetail> orderDetailList = orderDetailService.findByOrders_OrderId(orders.getOrderId());
                    int productID = 0;

                    for (OrderDetail od : orderDetailList) {
                        productID = od.getProductDetail().getProduct().getProductId();
                        ProductDetail productDetail = (ProductDetail) productDetailService.findById(od.getProductDetail().getProductDetailId());
                        productDetail.setQuantity(productDetail.getQuantity() - od.getQuantity());
                        productDetailService.saveOrUpdate(productDetail);
                    }
                    Set<ProductDetail> productDetailList = productDetailService.findByProduct_ProductId(productID);
                    Product product = (Product) productService.findById(productID);
                    int totalQuantity = 0;
                    for (ProductDetail pd : productDetailList) {
                        totalQuantity += pd.getQuantity();
                    }
                    product.setTotalQuantity(totalQuantity);
                    productService.saveOrUpdate(product);
                    Orders newOrder = new Orders();
                    newOrder.setUsers(users);
                    newOrder.setOrderStatus(0);
                    orderService.saveOrUpdate(newOrder);

                    return ResponseEntity.ok().body("Checkout success!");
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Checkout failed!");
        }
        return null;
    }

    @PostMapping("checkoutFlashSale")
    public ResponseEntity<?> checkoutFlashSale(@RequestParam("startFlashSale") LocalDate startFlashSale, @RequestParam("endFlashsale") LocalDate endFlashSale, @RequestBody OrderDetailRequest orderDetailRequest) {
        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = userService.findUsersByUserName(usersChangePass.getUsername());

        List<Orders> listOrder = orderService.findByUsers_UserIdAndCreateDateBetween(users.getUserId(), startFlashSale, endFlashSale);
        List<OrderDetail> listOrderdetail = orderDetailService.findByOrdersIn(listOrder);
        ProductDetail productDetail = productDetailService.findByColor_ColorHexAndSize_SizeName(orderDetailRequest.getColorHex(), orderDetailRequest.getSizeName());

        for (OrderDetail od : listOrderdetail) {
            if (od.getProductDetail().getProductDetailId()== productDetail.getProductDetailId()){
                return ResponseEntity.ok().body("Existed");
            }
        }
        return ResponseEntity.badRequest().body("Not exist");
    }
}
