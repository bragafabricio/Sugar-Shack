package co.maplr.sugarshack.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
public class OrderLineEntity {
    @Id
    String productId;
    @Column
    int qty;
}
