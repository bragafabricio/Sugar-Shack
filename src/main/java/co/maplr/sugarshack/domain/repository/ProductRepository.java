package co.maplr.sugarshack.domain.repository;

import co.maplr.sugarshack.domain.entity.MapleSyrupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<MapleSyrupEntity, String> {
    MapleSyrupEntity findProductById(String id);

    List<MapleSyrupEntity> findAll();
}
