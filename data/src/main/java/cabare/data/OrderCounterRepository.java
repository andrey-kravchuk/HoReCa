package cabare.data;

import cabare.entity.model.Cabare;
import cabare.entity.model.OrderCounter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderCounterRepository extends CrudRepository<OrderCounter, Long> {

  Optional<OrderCounter> findByCabare(Cabare cabare);
}