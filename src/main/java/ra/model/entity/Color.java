package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorId;
    private String colorHex;
    private String colorName;
    private boolean colorStatus;
    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private Set<ProductDetail> listProductDetail = new HashSet<>();
}
