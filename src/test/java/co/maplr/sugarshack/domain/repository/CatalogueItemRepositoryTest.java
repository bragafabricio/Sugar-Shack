package co.maplr.sugarshack.domain.repository;

import co.maplr.sugarshack.domain.entity.CatalogueItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CatalogueItemRepositoryTest {

    @Autowired
    CatalogueItemRepository repository;

    @Test
    void findByTypeTest() {

        String mapleName = "Green Maple Leaf";
        List<CatalogueItemEntity> testMaple = repository.findByType("CLEAR");

        assertNotNull(testMaple);
        assertEquals(mapleName, testMaple.get(0).getName());

    }

    @Test
    void findAllTest() {
        List<CatalogueItemEntity> testMaple = repository.findAll();

        assertNotNull(testMaple);
        assertEquals(6, testMaple.size());

    }
}