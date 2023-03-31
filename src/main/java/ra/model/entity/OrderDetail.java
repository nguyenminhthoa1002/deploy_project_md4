package ra.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productDetailId", referencedColumnName = "productDetailId")
    private ProductDetail productDetail;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Orders orders;
    private float price;
    private int quantity;
    private float totalMoneyOrderDetail;
}
