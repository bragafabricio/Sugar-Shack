package co.maplr.sugarshack.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnOkForCatalog() throws Exception {

        mvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkForCatalogWithType() throws Exception {

        mvc.perform(get("/products?type=amber"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkForProduct() throws Exception {

        mvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}