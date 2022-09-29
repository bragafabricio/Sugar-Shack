package co.maplr.sugarshack.domain.repository;

import co.maplr.sugarshack.domain.entity.MapleSyrupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<MapleSyrupEntity, String> {

    Optional<MapleSyrupEntity> findProductById(String id);

}
