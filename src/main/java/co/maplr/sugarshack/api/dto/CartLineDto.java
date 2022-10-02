package co.maplr.sugarshack.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartLineDto {
    @NotNull
    String productId;
    @NotNull
    String name;
    @NotNull
    String image;
    @NotNull
    double price;
    @NotNull
    int qty;
}
