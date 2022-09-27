package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.api.dto.MapleSyrupDto;
import co.maplr.sugarshack.domain.entity.MapleSyrupEntity;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import co.maplr.sugarshack.exception.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<MapleSyrupDto> findByType(String type) {
        List<MapleSyrupEntity> catalogue = new ArrayList<>();
        if(type != null){
            catalogue = productRepository.findByType(type.toUpperCase());
        } else {
            //Since type parameter is not required, in case it is missing, returns all the catalogue.
            catalogue = productRepository.findAll();
        }

        return catalogue.stream()
                .map(product -> mapper.map(product, MapleSyrupDto.class))
                .collect(Collectors.toList());
    }

}
