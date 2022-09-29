package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.api.dto.CatalogueItemDto;
import co.maplr.sugarshack.domain.entity.CatalogueItemEntity;
import co.maplr.sugarshack.domain.repository.CatalogueItemRepository;
import co.maplr.sugarshack.enums.MappleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CatalogueItemServiceTest {

    @MockBean
    CatalogueItemRepository repository;
    @InjectMocks
    @Autowired
    CatalogueItemService service;

    @Test
    void findByTypeGivenAType() {

        CatalogueItemEntity darkMapleType = new CatalogueItemEntity(
                "9",
                "Mrs. Betterworth's",
                "https://images.squarespace-cdn.com/content/v1/55d51211e4b09edbc4151a59/1586586578373-A9VXCULWU3T5DAJJT1GL/mb2.JPG?format=1000w",
                4.99, 99, "DARK");

        CatalogueItemEntity amberMapleType = new CatalogueItemEntity(
                "19",
                "Hungry Jack",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrDsAP1fyi-1WDg6ZCKvCaQKfgTSO-OZRYow&usqp=CAU",
                5.29, 99, "Amber");

        List<CatalogueItemEntity> expectedDark = Arrays.asList(darkMapleType);

        Mockito.when(repository.findByType("DARK")).thenReturn(expectedDark);

        List<CatalogueItemDto> testList = service.findByType(MappleType.DARK);

        assertEquals(1, testList.size());
        assertEquals(expectedDark.get(0).getId(), testList.get(0).getId());
        assertEquals(expectedDark.get(0).getName(), testList.get(0).getName());
        assertEquals(expectedDark.get(0).getImage(), testList.get(0).getImage());
        assertEquals(expectedDark.get(0).getPrice(), testList.get(0).getPrice());
        assertEquals(expectedDark.get(0).getMaxQty(), testList.get(0).getMaxQty());
        assertEquals(expectedDark.get(0).getType(), testList.get(0).getType().toString());

    }

}