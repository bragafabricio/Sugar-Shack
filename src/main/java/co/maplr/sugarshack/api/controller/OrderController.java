package co.maplr.sugarshack.api.controller;

import co.maplr.sugarshack.api.dto.OrderLineDto;
import co.maplr.sugarshack.api.dto.OrderValidationResponseDto;
import co.maplr.sugarshack.domain.service.OrderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@Tag(name = "order-controller", description = "Post orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/order")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<OrderValidationResponseDto> placeOrder(
            @RequestBody @NotNull List<OrderLineDto> orderLineDtoList) {

        OrderValidationResponseDto orderValidationResponse = orderService.save(orderLineDtoList);
        return new ResponseEntity<>(orderValidationResponse, HttpStatus.OK);
    }

}
