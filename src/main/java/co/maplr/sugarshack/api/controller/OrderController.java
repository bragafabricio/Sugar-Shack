package co.maplr.sugarshack.api.controller;

import co.maplr.sugarshack.api.dto.OrderLineDto;
import co.maplr.sugarshack.api.dto.OrderValidationResponseDto;
import co.maplr.sugarshack.domain.entity.OrderLineEntity;
import co.maplr.sugarshack.domain.entity.OrderValidationResponseEntity;
import co.maplr.sugarshack.domain.service.OrderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Tag(name = "order-controller", description = "Post orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper mapper;

    public OrderController(OrderService orderService, ModelMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/order")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<OrderValidationResponseDto> placeOrder(
            @RequestBody @NotNull List<OrderLineDto> orderLineDtoList) {

        OrderValidationResponseEntity orderValidationResponse = orderService.saveOrder(
                orderLineDtoList
                        .stream()
                        .map(order -> mapper.map(order, OrderLineEntity.class))
                        .collect(Collectors.toList()));

        return new ResponseEntity<>(
                mapper.map(orderValidationResponse, OrderValidationResponseDto.class), HttpStatus.OK);
    }

}
