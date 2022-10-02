package co.maplr.sugarshack.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_cartLine")
public class CartLineEntity {
    @Id
    private String productId;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    double price;
    @Column
    int qty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartLineEntity)) return false;
        CartLineEntity that = (CartLineEntity) o;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
