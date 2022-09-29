package co.maplr.sugarshack.api.controller;

import co.maplr.sugarshack.api.dto.CatalogueItemDto;
import co.maplr.sugarshack.api.dto.MapleSyrupDto;
import co.maplr.sugarshack.domain.service.CatalogueItemService;
import co.maplr.sugarshack.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "products-controller", description = "Maple Products Controller")
public class ProductController {

    private final ProductService productService;
    private final CatalogueItemService catalogueItemService;

    public ProductController(ProductService productService, CatalogueItemService catalogueItemService) {

        this.productService = productService;
        this.catalogueItemService = catalogueItemService;
    }

    @GetMapping
    @Operation(summary = "Get Maple Catalogue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved Maple Catalogue.")})
    public ResponseEntity<List<CatalogueItemDto>> getCatalogue(@RequestParam(required = false) String type) {
        return ResponseEntity.ok(
                catalogueItemService.findByType(type)
        );
    }

    @GetMapping(value = "{productId}")
    @Operation(summary = "Retrieve a maple product info by productId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product info.")})
    public ResponseEntity<MapleSyrupDto> getProductInfo(@NotNull @PathVariable(name = "productId") String productId) {
        return ResponseEntity.ok(
                productService.getProductById(productId)
        );
    }
}
