package co.maplr.sugarshack.api.controller;

import co.maplr.sugarshack.domain.entity.CartLineEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CartControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void getCart() throws Exception {
        mvc.perform(get("/cart"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addCart() throws Exception {
        mvc.perform(put("/cart?productId=1"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void changeQty() throws Exception {
        mvc.perform(patch("/cart?productId=1&newQty=11"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void removeFromCart() throws Exception {
        CartLineEntity cartLine = new CartLineEntity("11", "Greenish Maple Leaf", "ImageURL", 9.0, 10);

        mvc.perform(delete("/cart?productId=1"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }
}