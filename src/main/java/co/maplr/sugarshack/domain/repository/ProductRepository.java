package co.maplr.sugarshack.domain.repository;

import co.maplr.sugarshack.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    Optional<ProductEntity> findProductById(String id);
    List<ProductEntity> findByType(String type);


}
