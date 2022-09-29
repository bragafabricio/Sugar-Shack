package co.maplr.sugarshack.api.dto;

import co.maplr.sugarshack.enums.MappleType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CatalogueItemDto {
    @NotNull
    String id;
    @NotNull
    String name;
    @NotNull
    String image;
    @NotNull
    double price;
    @NotNull
    int maxQty;
    @NotNull
    MappleType type;
}
