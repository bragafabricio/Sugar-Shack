package co.maplr.sugarshack.api.dto;

import co.maplr.sugarshack.enums.MappleType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MapleSyrupDto {

    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String image;
    @NotEmpty
    private double price;
    @NotEmpty
    private int stock;
    @NotEmpty
    private MappleType type;

}
