package co.maplr.sugarshack.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class OrderValidationResponseDto {
    @NotNull
    Boolean isOrderValid;
    List<String> errors;
}
