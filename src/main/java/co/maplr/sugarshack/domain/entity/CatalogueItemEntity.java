package co.maplr.sugarshack.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_catalogueItem")
public class CatalogueItemEntity {

    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    double price;
    @Column
    private int maxQty;
    @Column
    private String type;
}
