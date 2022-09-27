package co.maplr.sugarshack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String productId) {
        super(String.format("We couldn't find the product %s. Is this a new kind of Maple Syrup?", productId));
    }
}
