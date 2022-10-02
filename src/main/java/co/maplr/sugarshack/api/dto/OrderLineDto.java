package co.maplr.sugarshack.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderLineDto {
    @NotNull
    String productId;
    @NotNull
    int qty;
}
