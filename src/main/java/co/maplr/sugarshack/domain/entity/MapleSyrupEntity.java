package co.maplr.sugarshack.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table(name = "tbl_mapleSyrup")
public class MapleSyrupEntity {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    double price;
    @Column
    private int stock;
    @Column
    private String type;

    public MapleSyrupEntity() {
    }

    public MapleSyrupEntity(String id, String name, String description, String image, double price, int stock, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapleSyrupEntity)) return false;
        MapleSyrupEntity that = (MapleSyrupEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
