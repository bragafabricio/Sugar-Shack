package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.domain.entity.CartLineEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Test
    void findCarts() {
        cartService.saveCartLine("11");
        cartService.saveCartLine("22");
        cartService.saveCartLine("33");

        List<CartLineEntity> response = cartService.findCarts();

        assertEquals(3, response.size());
        assertEquals("11", response.get(0).getProductId());
        assertEquals("22", response.get(1).getProductId());
        assertEquals("33", response.get(2).getProductId());

    }

    @Test
    void saveCartLine() {

        CartLineEntity response = cartService.saveCartLine("11");
        CartLineEntity expectedResponse = new CartLineEntity("11", "Greenish Maple Leaf", "ImageURL", 9.0, 0);

        assertEquals(expectedResponse, response);
    }

    @Test
    void delete() {
        cartService.deleteAll();
        cartService.saveCartLine("11");
        assertEquals(1, cartService.findCarts().size());
        cartService.delete("11");
        assertEquals(0, cartService.findCarts().size());
    }

    @Test
    void update() {
        cartService.saveCartLine("11");
        cartService.update("11", 10);

        int response = cartService.findCarts().get(0).getQty();
        assertEquals(10, response);
    }
}