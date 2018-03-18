package cabare.data;

import cabare.entity.model.Bill;
import cabare.entity.model.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

  Optional<Bill> findById(Long billId);

  @Query("select b from Bill b where b.opened = true and b.employee = ?1")
  List<Bill> findOpenedByEmployee(Employee employee);
}
