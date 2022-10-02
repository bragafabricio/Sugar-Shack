package co.maplr.sugarshack.api.controller;

import co.maplr.sugarshack.api.dto.CartLineDto;
import co.maplr.sugarshack.domain.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Tag(name = "cart-controller", description = "Cart Controller")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/cart")
    @Operation(summary = "Get CartLine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<List<CartLineDto>> getCart() {
        return ResponseEntity.ok(
                cartService.findCarts()
        );
    }

    @PutMapping(value = "/cart")
    @Operation(summary = "Update CartLine")
    @ApiResponse(responseCode = "202", description = "Accepted")
    public ResponseEntity<Void> addCart(
            @RequestParam @NotNull String productId) {
        cartService.save(productId);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping(value = "/cart")
    @Operation(summary = "Update CartLine Product's Quantity")
    @ApiResponse(responseCode = "202", description = "Accepted")
    public ResponseEntity<Void> changeQty(
            @RequestParam @NotNull String productId, int newQty) {
        cartService.update(productId, newQty);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/cart")
    @Operation(summary = "Delete CartLine")
    @ApiResponse(responseCode = "202", description = "Accepted")
    public ResponseEntity<Void> removeFromCart(
            @RequestParam @NotNull String productId) {
        cartService.delete(productId);
        return ResponseEntity.accepted().build();
    }

}
