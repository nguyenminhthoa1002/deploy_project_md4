package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catalogId;
    private String catalogName;
    private String catalogDescription;
//    @JsonIgnore
    private int catalogParentId;
    private String catalogParentName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime catalogCreateDate;
    private boolean catalogStatus;
    @OneToMany(mappedBy = "catalog")
    @JsonIgnore
    private Set<Product> listProduct = new HashSet<>();

}
