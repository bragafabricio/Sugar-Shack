package co.maplr.sugarshack.repository;

import co.maplr.sugarshack.model.MapleSyrup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<MapleSyrup, String> {
    MapleSyrup findProductById(String id);

    List<MapleSyrup> findAll();
}
