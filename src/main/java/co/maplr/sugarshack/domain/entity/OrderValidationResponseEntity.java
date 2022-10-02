package co.maplr.sugarshack.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderValidationResponseEntity {

    @NotNull
    Boolean isOrderValid;
    List<String> errors;

}
