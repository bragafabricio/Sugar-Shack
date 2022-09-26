package co.maplr.sugarshack.service;

import co.maplr.sugarshack.model.MapleSyrup;
import co.maplr.sugarshack.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public MapleSyrup getProductById(String productId) {
        MapleSyrup product = productRepository.findProductById(productId);
        return product;
    }

    public List<MapleSyrup> findAll() {
        List<MapleSyrup> products = productRepository.findAll();
        return products;
    }

}
