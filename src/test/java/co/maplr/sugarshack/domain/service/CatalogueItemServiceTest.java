package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.domain.entity.ProductEntity;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import co.maplr.sugarshack.enums.MappleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CatalogueItemServiceTest {

    @MockBean
    ProductRepository repository;
    @InjectMocks
    @Autowired
    CatalogueItemService service;

    @Test
    void findByTypeGivenAType() {

        ProductEntity darkMapleType = new ProductEntity("22", "Goldish Maple Leaf",
                "Looks like Golden Syrup, but it is honey actually.",
                "ImageURL", 19.0, 99, 10, "DARK");

        ProductEntity amberMapleType = new ProductEntity("33", "Reddish Maple Leaf",
                "Watch out! This is Corn Syrup! That's how it should be labelled.",
                "ImageURL", 4.5, 999, 10, "AMBER");

        List<ProductEntity> expectedDark = Collections.singletonList(darkMapleType);

        Mockito.when(repository.findByType("DARK")).thenReturn(expectedDark);

        List<ProductEntity> testList = service.findByType(MappleType.DARK);

        assertEquals(1, testList.size());
        assertEquals(expectedDark.get(0).getId(), testList.get(0).getId());
        assertEquals(expectedDark.get(0).getName(), testList.get(0).getName());
        assertEquals(expectedDark.get(0).getImage(), testList.get(0).getImage());
        assertEquals(expectedDark.get(0).getPrice(), testList.get(0).getPrice());
        assertEquals(expectedDark.get(0).getMaxQty(), testList.get(0).getMaxQty());
        assertEquals(expectedDark.get(0).getType(), testList.get(0).getType());

    }

}