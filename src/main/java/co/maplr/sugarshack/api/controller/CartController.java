package co.maplr.sugarshack.api.controller;

import co.maplr.sugarshack.api.dto.CartLineDto;
import co.maplr.sugarshack.domain.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "cart-controller", description = "Cart Controller")
public class CartController {

    private final CartService cartService;
    private final ModelMapper mapper;

    public CartController(CartService cartService, ModelMapper mapper) {

        this.cartService = cartService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/cart")
    @Operation(summary = "Get CartLine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<List<CartLineDto>> getCart() {
        return ResponseEntity.ok(
                cartService.findCarts()
                        .stream()
                        .map(cartLineEntity -> mapper.map(cartLineEntity, CartLineDto.class))
                        .collect(Collectors.toList())
        );
    }

    @PutMapping(value = "/cart")
    @Operation(summary = "Create CartLine")
    @ApiResponse(responseCode = "202", description = "Accepted")
    public ResponseEntity<Void> addCart(
            @RequestParam @NotNull String productId) {
        cartService.saveCartLine(productId);
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
