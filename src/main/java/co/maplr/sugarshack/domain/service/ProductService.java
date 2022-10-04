package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.domain.entity.ProductEntity;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import co.maplr.sugarshack.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity getProductById(String productId) {

        return productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public ProductEntity updateProductStock(ProductEntity productEntity, int newStock) {
        productEntity.setStock(newStock);
        return productRepository.save(productEntity);
    }

}
