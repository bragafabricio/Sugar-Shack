package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.domain.entity.ProductEntity;
import co.maplr.sugarshack.domain.repository.ProductRepository;
import co.maplr.sugarshack.enums.MappleType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueItemService {
    ProductRepository productRepository;
    ModelMapper mapper;

    @Autowired
    public CatalogueItemService(ProductRepository catalogueItem, ModelMapper mapper) {
        this.productRepository = catalogueItem;
        this.mapper = mapper;
    }

    public List<ProductEntity> findByType(MappleType type) {

        return productRepository.findByType(type.toString().toUpperCase());

    }
}
