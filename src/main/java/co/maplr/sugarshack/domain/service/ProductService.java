package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.api.dto.MapleSyrupDto;
import co.maplr.sugarshack.domain.entity.MapleSyrupEntity;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import co.maplr.sugarshack.exception.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    ProductRepository productRepository;
    ModelMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public MapleSyrupDto getProductById(String productId) {

        MapleSyrupEntity product = productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return mapper.map(product, MapleSyrupDto.class);
    }

    public MapleSyrupEntity saveProduct(MapleSyrupEntity mapleSyrupEntity) {
        return productRepository.save(mapleSyrupEntity);
    }

}
