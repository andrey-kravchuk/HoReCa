package cabare.data;

import cabare.entity.model.Cabare;
import cabare.entity.model.OrderCounter;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCounterRepository extends CrudRepository<OrderCounter, Long> {

  Optional<OrderCounter> findByCabare(Cabare cabare);
}