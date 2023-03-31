package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productdetail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productDetailId;
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId",referencedColumnName = "productId")
    @JsonIgnore
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "colorId", referencedColumnName = "colorId")
    private Color color;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sizeId", referencedColumnName = "sizeId")
    private Size size;
    private boolean productDetailStatus;
    @OneToMany(mappedBy = "productDetail")
    @JsonIgnore
    private Set<OrderDetail> listOrderDetail = new HashSet<>();
}
