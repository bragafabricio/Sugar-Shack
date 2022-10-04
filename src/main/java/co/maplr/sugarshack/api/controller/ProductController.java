package co.maplr.sugarshack.api.controller;

import co.maplr.sugarshack.api.dto.CatalogueItemDto;
import co.maplr.sugarshack.api.dto.MapleSyrupDto;
import co.maplr.sugarshack.domain.service.CatalogueItemService;
import co.maplr.sugarshack.domain.service.ProductService;
import co.maplr.sugarshack.enums.MappleType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "products-controller", description = "Maple Products Controller")
public class ProductController {

    private final ProductService productService;
    private final CatalogueItemService catalogueItemService;
    private final ModelMapper mapper;

    public ProductController(ProductService productService, CatalogueItemService catalogueItemService, ModelMapper mapper) {
        this.productService = productService;
        this.catalogueItemService = catalogueItemService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/products")
    @Operation(summary = "Get Maple Catalogue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved Maple Catalogue.")})
    public ResponseEntity<List<CatalogueItemDto>> getCatalogue(@RequestParam(required = false) MappleType type) {
        return ResponseEntity.ok(
                catalogueItemService.findByType(type)
                        .stream()
                        .map(product -> mapper.map(product, CatalogueItemDto.class))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/products/{productId}")
    @Operation(summary = "Retrieve a maple product info by productId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product info.")})
    public ResponseEntity<MapleSyrupDto> getProductInfo(@NotNull @PathVariable(name = "productId") String productId) {
        return ResponseEntity.ok(
                mapper.map(productService.getProductById(productId), MapleSyrupDto.class)
        );
    }
}
