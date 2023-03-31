package ra.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private String productDescription;
    private float productImportPrice;
    private float productExportPrice;
    private int totalQuantity;
    private String productImg;
    private LocalDateTime productCreateDate;
    private boolean productStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalogId", referencedColumnName = "catalogId")
    private Catalog catalog;
    @OneToMany(mappedBy = "product")
    private Set<ProductDetail> listProductDetail = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Product_Color", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "colorId"))
    private Set<Color> listColor = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Product_Size", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "sizeId"))
    private Set<Size> listSize = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Image> listSubImage = new HashSet<>();

}
