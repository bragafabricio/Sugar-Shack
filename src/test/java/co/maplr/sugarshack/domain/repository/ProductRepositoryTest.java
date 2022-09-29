package co.maplr.sugarshack.domain.repository;

import co.maplr.sugarshack.domain.entity.MapleSyrupEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Optional<MapleSyrupEntity> testMaple = repository.findProductById("11");

        assertNotNull(testMaple);
        assertEquals(mapleName, testMaple.get().getName());

    }
}