package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.api.dto.MapleSyrupDto;
import co.maplr.sugarshack.api.dto.OrderLineDto;
import co.maplr.sugarshack.api.dto.OrderValidationResponseDto;
import co.maplr.sugarshack.domain.entity.MapleSyrupEntity;
import co.maplr.sugarshack.domain.entity.OrderLineEntity;
import co.maplr.sugarshack.domain.entity.OrderValidationResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Service
public class OrderService {

    private final ModelMapper mapper;

    private final ProductService productService;

    @Autowired
    public OrderService(ModelMapper mapper, ProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }

    public OrderValidationResponseDto save(List<OrderLineDto> orderLineDtoList) {

        boolean isValid = true;
        List<String> errors = new ArrayList<>();

        for (OrderLineDto order : orderLineDtoList) {
            if (!isAbleToSaveOrder(order)) {
                isValid = false;
                int stock = productService.getProductById(order.getProductId()).getStock();
                errors.add(String.format(
                        "Not enough of product %s in stock. We have only %d of this left.",
                        order.getProductId(), stock));
            }
        }

        OrderValidationResponseEntity response = new OrderValidationResponseEntity(isValid, errors);
        return mapper.map(response, OrderValidationResponseDto.class);
    }

    private boolean isAbleToSaveOrder(OrderLineDto orderLine) {

        boolean isOrderValid = true;
        OrderLineEntity order = mapper.map(orderLine, OrderLineEntity.class);
        MapleSyrupDto mapleSyrupDto = productService.getProductById(order.getProductId());
        int newStock = mapleSyrupDto.getStock() - abs(order.getQty());

        if (newStock >= 0) {
            mapleSyrupDto.setStock(newStock);
            productService.saveProduct(mapper.map(mapleSyrupDto, MapleSyrupEntity.class));
        } else {
            isOrderValid = false;
        }

        return isOrderValid;
    }
}
