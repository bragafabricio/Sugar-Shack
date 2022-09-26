package co.maplr.sugarshack.dto;

import co.maplr.sugarshack.enums.MappleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapleSyrupDto {

    private String id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int stock;
    private MappleType type;

}
