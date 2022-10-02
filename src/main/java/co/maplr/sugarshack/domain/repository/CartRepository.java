package co.maplr.sugarshack.domain.repository;

import co.maplr.sugarshack.domain.entity.CartLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartLineEntity, String> {

}
