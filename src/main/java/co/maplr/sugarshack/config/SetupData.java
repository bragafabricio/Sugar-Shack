package co.maplr.sugarshack.config;

import co.maplr.sugarshack.model.MapleSyrup;
import co.maplr.sugarshack.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupData implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new MapleSyrup("1", "Green Maple Leaf",
                "This Maple is so pure that it speaks quebecois.",
                "ImageURL", 10.0, 100, "CLEAR"));

        productRepository.save(new MapleSyrup("2", "Golden Maple Leaf",
                "This Maple Syrup is so tasty that the Government made a coin to pay tribute to it.",
                "ImageURL", 20.0, 100, "DARK"));

        productRepository.save(new MapleSyrup("3", "Red Maple Leaf",
                "Everyone's favorite Maple Syrup. It's a 50/50 blend of Green and Golden Maple Leaf.",
                "ImageURL", 15.0, 100, "AMBER"));
    }
}
