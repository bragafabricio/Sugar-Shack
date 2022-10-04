package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.domain.entity.OrderLineEntity;
import co.maplr.sugarshack.domain.entity.OrderValidationResponseEntity;
import co.maplr.sugarshack.domain.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Service
public class OrderService {

    private final ProductService productService;

    @Autowired
    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    public OrderValidationResponseEntity saveOrder(List<OrderLineEntity> orderLineEntityList) {

        boolean isValid = true;
        List<String> errors = new ArrayList<>();

        for (OrderLineEntity order : orderLineEntityList) {
            //isValid = true if, and only if, both "isLessThanMaxQty" and "isStockEnough" are true
            boolean isOrderLineValid = (isLessThanMaxQty(order, errors) && isStockEnough(order, errors));
            if (isOrderLineValid) {
                updateStock(order.getProductId(), order.getQty());
            } else {
                isValid = false;
                //If one orderline is invalid, all orderlines are invalid
            }
        }

        return new OrderValidationResponseEntity(isValid, errors);
    }

    private void updateStock(String productId, int orderQty) {
        ProductEntity mapleSyrup = productService.getProductById(productId);
        int newStock = mapleSyrup.getStock() - orderQty;
        productService.updateProductStock(mapleSyrup, newStock);
    }

    private boolean isLessThanMaxQty(OrderLineEntity order, List<String> errors) {
        boolean isUnderMaxQty = true;
        int maxQty = productService.getProductById(order.getProductId()).getMaxQty();
        if (maxQty < order.getQty()) {
            isUnderMaxQty = false;
            errors.add(String.format(
                    "It looks like somebody's got a sweet tooth! Orders with more than %d are not allowed.", maxQty));
        }
        return isUnderMaxQty;
    }

    private boolean isStockEnough(OrderLineEntity orderLine, List<String> errorsList) {

        boolean isStockHigherThanOrderQty;
        String productId = orderLine.getProductId();
        int currentStock = productService.getProductById(productId).getStock();
        int newStock = currentStock - abs(orderLine.getQty());

        if (newStock >= 0) {
            isStockHigherThanOrderQty = true;
        } else {
            isStockHigherThanOrderQty = false;
            errorsList.add(String.format(
                    "Not enough of product %s in stock. We have only %d of this left.", productId, currentStock));
        }

        return isStockHigherThanOrderQty;
    }
}
