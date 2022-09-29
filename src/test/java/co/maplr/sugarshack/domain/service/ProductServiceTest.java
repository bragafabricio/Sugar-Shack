package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.api.dto.MapleSyrupDto;
import co.maplr.sugarshack.domain.entity.MapleSyrupEntity;
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

import java.util.Arrays;
import java.util.List;
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

        MapleSyrupEntity expectedMaple = new MapleSyrupEntity("9",
                "Mrs. Betterworth's",
                "Butter or Syrup, who can tell?",
                "https://images.squarespace-cdn.com/content/v1/55d51211e4b09edbc4151a59/1586586578373-A9VXCULWU3T5DAJJT1GL/mb2.JPG?format=1000w",
                4.99, 99, "DARK");
        Mockito.when(repository.findProductById("9")).thenReturn(Optional.of(expectedMaple));

        MapleSyrupDto testMaple = service.getProductById("9");
        assertEquals(expectedMaple.getId(), testMaple.getId());
        assertEquals(expectedMaple.getName(), testMaple.getName());
        assertEquals(expectedMaple.getDescription(), testMaple.getDescription());
        assertEquals(expectedMaple.getImage(), testMaple.getImage());
        assertEquals(expectedMaple.getPrice(), testMaple.getPrice());
        assertEquals(expectedMaple.getStock(), testMaple.getStock());
        assertEquals(expectedMaple.getType(), testMaple.getType().toString());

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

    @Test
    void findByTypeGivenAType() {

        MapleSyrupEntity darkMapleType = new MapleSyrupEntity("9",
                "Mrs. Betterworth's",
                "Butter or Syrup, who can tell?",
                "https://images.squarespace-cdn.com/content/v1/55d51211e4b09edbc4151a59/1586586578373-A9VXCULWU3T5DAJJT1GL/mb2.JPG?format=1000w",
                4.99, 99, "DARK");

        MapleSyrupEntity amberMapleType = new MapleSyrupEntity("19",
                "Hungry Jack",
                "Is your Name Jack or are you Hungry? This one might be for you!",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrDsAP1fyi-1WDg6ZCKvCaQKfgTSO-OZRYow&usqp=CAU",
                5.29, 99, "Amber");

        List<MapleSyrupEntity> expectedDark = Arrays.asList(darkMapleType);

        Mockito.when(repository.findByType("DARK")).thenReturn(expectedDark);

        List<MapleSyrupDto> testList = service.findByType("Dark");

        assertEquals(1, testList.size());
        assertEquals(expectedDark.get(0).getId(), testList.get(0).getId());
        assertEquals(expectedDark.get(0).getName(), testList.get(0).getName());
        assertEquals(expectedDark.get(0).getDescription(), testList.get(0).getDescription());
        assertEquals(expectedDark.get(0).getImage(), testList.get(0).getImage());
        assertEquals(expectedDark.get(0).getPrice(), testList.get(0).getPrice());
        assertEquals(expectedDark.get(0).getStock(), testList.get(0).getStock());
        assertEquals(expectedDark.get(0).getType(), testList.get(0).getType().toString());

    }

    @Test
    void findByTypeWithNoTypeGiven() {

        MapleSyrupEntity darkMapleType = new MapleSyrupEntity("9",
                "Mrs. Betterworth's",
                "Butter or Syrup, who can tell?",
                "https://images.squarespace-cdn.com/content/v1/55d51211e4b09edbc4151a59/1586586578373-A9VXCULWU3T5DAJJT1GL/mb2.JPG?format=1000w",
                4.99, 99, "DARK");

        MapleSyrupEntity amberMapleType = new MapleSyrupEntity("19",
                "Hungry Jack",
                "Is your Name Jack or are you Hungry? This one might be for you!",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrDsAP1fyi-1WDg6ZCKvCaQKfgTSO-OZRYow&usqp=CAU",
                5.29, 99, "Amber");

        List<MapleSyrupEntity> expectedAll = Arrays.asList(darkMapleType, amberMapleType);

        Mockito.when(repository.findByType("")).thenReturn(expectedAll);

        List<MapleSyrupDto> testList = service.findByType("");

        assertEquals(2, testList.size());

    }

}