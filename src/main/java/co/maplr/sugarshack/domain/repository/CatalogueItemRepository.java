package co.maplr.sugarshack.domain.repository;

import co.maplr.sugarshack.domain.entity.CatalogueItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogueItemRepository extends JpaRepository<CatalogueItemEntity, String> {

    List<CatalogueItemEntity> findByType(String type);

}
