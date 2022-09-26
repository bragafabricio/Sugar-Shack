package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.api.dto.MapleSyrupDto;
import co.maplr.sugarshack.domain.entity.MapleSyrupEntity;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        MapleSyrupEntity product = productRepository.findProductById(productId);
        return mapper.map(product, MapleSyrupDto.class);
    }

    public List<MapleSyrupDto> findAll() {
        List<MapleSyrupEntity> catalogue = productRepository.findAll();

        return catalogue.stream()
                .map(product -> mapper.map(product, MapleSyrupDto.class))
                .collect(Collectors.toList());
    }

}
