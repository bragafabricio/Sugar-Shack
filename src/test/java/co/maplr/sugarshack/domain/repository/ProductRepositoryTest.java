package co.maplr.sugarshack.domain.repository;

import co.maplr.sugarshack.domain.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void findProductByIdTest() {
        String mapleName = "Greenish Maple Leaf";
        Optional<ProductEntity> testMaple = repository.findProductById("11");

        assertNotNull(testMaple);
        assertEquals(mapleName, testMaple.get().getName());

    }
    @Test
    void findByTypeTest() {

        String mapleName = "Green Maple Leaf";
        List<ProductEntity> testMaple = repository.findByType("CLEAR");

        assertNotNull(testMaple);
        assertEquals(mapleName, testMaple.get(0).getName());
    }

    @Test
    void findAllTest() {
        List<ProductEntity> testMaple = repository.findAll();

        assertNotNull(testMaple);
        assertEquals(6, testMaple.size());

    }
}