package co.maplr.sugarshack.config;

import co.maplr.sugarshack.domain.entity.CartLineEntity;
import co.maplr.sugarshack.domain.entity.ProductEntity;
import co.maplr.sugarshack.domain.repository.CartRepository;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupData implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public void run(String... args) throws Exception {

        //productRepository Set Up
        productRepository.save(new ProductEntity("1", "Green Maple Leaf",
                "This Maple is so pure that it speaks quebecois.",
                "ImageURL", 10.0, 100, 10, "CLEAR"));

        productRepository.save(new ProductEntity("2", "Golden Maple Leaf",
                "This Maple Syrup is so tasty that the Government made a coin to pay tribute to it.",
                "ImageURL", 20.0, 100, 10, "DARK"));

        productRepository.save(new ProductEntity("3", "Red Maple Leaf",
                "Everyone's favorite Maple Syrup. It's a 50/50 blend of Green and Golden Maple Leaf.",
                "ImageURL", 15.0, 100, 10, "AMBER"));

        productRepository.save(new ProductEntity("11","Mrs. Betterworth's",
                "Mrs. Butterworth's instantly transports us back to our childhood. When we didn't know how pure maple syrup tasted like!",
                "https://images.squarespace-cdn.com/content/v1/55d51211e4b09edbc4151a59/1586586578373-A9VXCULWU3T5DAJJT1GL/mb2.JPG?format=1000w",
                4.99, 99, 10, "DARK"));

        productRepository.save(new ProductEntity("22","Hungry Jack",
                "If you're out of maple sap, your best bet is Hungry Jack!",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrDsAP1fyi-1WDg6ZCKvCaQKfgTSO-OZRYow&usqp=CAU",
                4.29, 99, 10, "AMBER"));

        productRepository.save(new ProductEntity("33", "Log Cabin",
                "Having your in laws for breakfast? Log Cabin is just fine for the occasion!",
                "https://images-na.ssl-images-amazon.com/images/I/61L+ab53JFL._AC_UL600_SR600,600_.jpg",
                2.99, 999, 10, "CLEAR"));

        //cartRepository Set Up

        cartRepository.save(new CartLineEntity("1",
                "Green Maple Leaf",
                "ImageURL",
                10.0,
                1));

        cartRepository.save(new CartLineEntity("2",
                "Golden Maple Leaf",
                "ImageURL",
                20.0,
                1));

    }
}
