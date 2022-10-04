package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.domain.entity.OrderLineEntity;
import co.maplr.sugarshack.domain.entity.OrderValidationResponseEntity;
import co.maplr.sugarshack.domain.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OrderServiceTest {

    @MockBean
    ProductService mockProductService;
    @InjectMocks
    @Autowired
    OrderService orderService;

    @BeforeEach
    void setupTest() {
        Mockito.when(mockProductService.getProductById("11")).thenReturn(
                new ProductEntity("11", "Greenish Maple Leaf",
                        "This Maple Syrup looks like original, but it's just a mock!",
                        "ImageURL", 9.0, 100, 10, "CLEAR"));

        Mockito.when(mockProductService.getProductById("22")).thenReturn(
                new ProductEntity("22", "Goldish Maple Leaf",
                        "Looks like Golden Syrup, but it is honey actually.",
                        "ImageURL", 19.0, 5, 10, "DARK"));
    }

    @Test
    void shouldSaveOrderIsLessThanMaxQty() {


        List<OrderLineEntity> orderLineList = Arrays.asList(
                new OrderLineEntity("11", 10),
                new OrderLineEntity("22", 5)
        );
        OrderValidationResponseEntity expectedResponse = new OrderValidationResponseEntity(true, Collections.emptyList());
        OrderValidationResponseEntity response = orderService.saveOrder(orderLineList);

        assertEquals(expectedResponse.getIsOrderValid(), response.getIsOrderValid());
        assertEquals(expectedResponse.getErrors(), response.getErrors());
    }

    @Test
    void shouldNotSaveOrderIsHigherThanMaxQty() {
        List<OrderLineEntity> orderLineList = Arrays.asList(
                new OrderLineEntity("11", 11),
                new OrderLineEntity("22", 5)
        );

        String expectedError = "It looks like somebody's got a sweet tooth! Orders with more than 10 are not allowed.";
        OrderValidationResponseEntity expectedResponse = new OrderValidationResponseEntity(
                false, Collections.singletonList(expectedError));
        OrderValidationResponseEntity response = orderService.saveOrder(orderLineList);

        assertEquals(expectedResponse.getIsOrderValid(), response.getIsOrderValid());
        assertEquals(expectedResponse.getErrors(), response.getErrors());

    }

    @Test
    void shouldNotSaveOrdernotEnoughStock() {
        List<OrderLineEntity> orderLineList = Arrays.asList(
                new OrderLineEntity("11", 10),
                new OrderLineEntity("22", 6)
        );

        String expectedError = "Not enough of product 22 in stock. We have only 5 of this left.";
        OrderValidationResponseEntity expectedResponse = new OrderValidationResponseEntity(
                false, Collections.singletonList(expectedError));
        OrderValidationResponseEntity response = orderService.saveOrder(orderLineList);

        assertEquals(expectedResponse.getIsOrderValid(), response.getIsOrderValid());
        assertEquals(expectedResponse.getErrors(), response.getErrors());

    }

    @Test
    void shouldNotSaveWhenOrderIsHigherThanMaxQtyAndWhenOrdernotEnoughStock() {
        List<OrderLineEntity> orderLineList = Arrays.asList(
                new OrderLineEntity("11", 11),
                new OrderLineEntity("22", 6)
        );

        String expectedError1 = "It looks like somebody's got a sweet tooth! Orders with more than 10 are not allowed.";
        String expectedError2 = "Not enough of product 22 in stock. We have only 5 of this left.";
                OrderValidationResponseEntity expectedResponse = new OrderValidationResponseEntity(
                false, Arrays.asList(expectedError1, expectedError2));
        OrderValidationResponseEntity response = orderService.saveOrder(orderLineList);

        assertEquals(expectedResponse.getIsOrderValid(), response.getIsOrderValid());
        assertEquals(expectedResponse.getErrors(), response.getErrors());

    }

}