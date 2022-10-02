package co.maplr.sugarshack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CartLineNotFoundException extends RuntimeException {

    public CartLineNotFoundException(String productId) {
        super(String.format("We couldn't find the cartLine for the product %s. Somebody will not get his Maple taffy today :( !", productId));
    }
}
