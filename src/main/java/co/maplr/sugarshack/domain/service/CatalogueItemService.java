package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.api.dto.CatalogueItemDto;
import co.maplr.sugarshack.domain.entity.CatalogueItemEntity;
import co.maplr.sugarshack.domain.repository.CatalogueItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogueItemService {
    CatalogueItemRepository catalogueItemRepository;
    ModelMapper mapper;

    @Autowired
    public CatalogueItemService(CatalogueItemRepository catalogueItemRepository, ModelMapper mapper) {
        this.catalogueItemRepository = catalogueItemRepository;
        this.mapper = mapper;
    }

    public List<CatalogueItemDto> findByType(String type) {
        List<CatalogueItemEntity> catalogue = new ArrayList<>();
        if (type != null) {
            catalogue = catalogueItemRepository.findByType(type.toUpperCase());
        } else {
            //Since type parameter is not required, in case it is missing, returns all the catalogue.
            catalogue = catalogueItemRepository.findAll();
        }

        return catalogue.stream()
                .map(product -> mapper.map(product, CatalogueItemDto.class))
                .collect(Collectors.toList());
    }
}
