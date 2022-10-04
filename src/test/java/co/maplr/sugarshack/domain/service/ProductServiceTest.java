package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.domain.entity.ProductEntity;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import co.maplr.sugarshack.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @MockBean
    ProductRepository repository;
    @InjectMocks
    @Autowired
    ProductService service;

    @Test
    void getProductByIdFoundTest() {

        ProductEntity expectedMaple = new ProductEntity("9",
                "Mrs. Betterworth's",
                "Butter or Syrup, who can tell?",
                "https://images.squarespace-cdn.com/content/v1/55d51211e4b09edbc4151a59/1586586578373-A9VXCULWU3T5DAJJT1GL/mb2.JPG?format=1000w",
                4.99, 99, 10, "DARK");
        Mockito.when(repository.findProductById("9")).thenReturn(Optional.of(expectedMaple));

        ProductEntity testMaple = service.getProductById("9");
        assertEquals(expectedMaple.getId(), testMaple.getId());
        assertEquals(expectedMaple.getName(), testMaple.getName());
        assertEquals(expectedMaple.getDescription(), testMaple.getDescription());
        assertEquals(expectedMaple.getImage(), testMaple.getImage());
        assertEquals(expectedMaple.getPrice(), testMaple.getPrice());
        assertEquals(expectedMaple.getStock(), testMaple.getStock());
        assertEquals(expectedMaple.getType(), testMaple.getType());

    }

    @Test
    void getProductByIdNotFoundTest() {

        Mockito.when(repository.findProductById("0")).thenReturn(Optional.empty());

        ProductNotFoundException e = assertThrows(
                ProductNotFoundException.class,
                () -> service.getProductById("0"));

        assertEquals("We couldn't find the product 0. Is this a new kind of Maple Syrup?",
                e.getMessage());

    }

}