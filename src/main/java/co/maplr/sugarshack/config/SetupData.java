package co.maplr.sugarshack.config;

import co.maplr.sugarshack.domain.entity.ProductEntity;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupData implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

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

        productRepository.save(new ProductEntity("11", "Greenish Maple Leaf",
                "This Maple Syrup looks like original, but it's just a mock!",
                "ImageURL", 9.0, 99, 10, "CLEAR"));

        productRepository.save(new ProductEntity("22", "Goldish Maple Leaf",
                "Looks like Golden Syrup, but it is honey actually.",
                "ImageURL", 19.0, 99, 10, "DARK"));

        productRepository.save(new ProductEntity("33", "Reddish Maple Leaf",
                "Watch out! This is Corn Syrup! That's how it should be labelled.",
                "ImageURL", 4.5, 999, 10, "AMBER"));

    }
}
