package co.maplr.sugarshack.controller;

import co.maplr.sugarshack.dto.MapleSyrupDto;
import co.maplr.sugarshack.model.MapleSyrup;
import co.maplr.sugarshack.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Products", description = "Maple Products Controller")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper mapper;

    public ProductController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping(value = "products/{productId}")
    @Operation(summary = "Retrieve a maple product info by productId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product info."),
    })
    public ResponseEntity<MapleSyrupDto> getProductInfo(@NotNull @PathVariable(name = "productId") String productId) {
        MapleSyrup product = productService.getProductById(productId);
        MapleSyrupDto productDto = mapper.map(product, MapleSyrupDto.class);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping(value = "products")
    @Operation(summary = "Get Maple Catalogue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved Maple Catalogue."),
    })
    public ResponseEntity<List<MapleSyrupDto>> getCatalogue() {
        List<MapleSyrup> catalogue = productService.findAll();

        return ResponseEntity.ok(
                                catalogue.stream()
                                    .map(product -> mapper.map(product, MapleSyrupDto.class))
                                    .collect(Collectors.toList()));
    }
}
