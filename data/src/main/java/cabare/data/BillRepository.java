package cabare.data;

import cabare.entity.model.Bill;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

  Optional<Bill> findById(Long billId);
}
