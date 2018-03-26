package cabare.data;

import cabare.entity.model.Bill;
import cabare.entity.model.Employee;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

  Optional<Bill> findById(Long billId);

  @Query("select b from Bill b where b.opened = true and b.employee = ?1")
  List<Bill> findOpenedByEmployee(Employee employee);

  @Query("select b from Bill b where b.activeShift = true and b.employee = ?1")
  List<Bill> findCurrentShiftBillsByEmployee(Employee employee);

  @Query("select b from Bill b where b.employee = ?1 and b.opened = true and b.tableNumber = ?2")
  Slice<Bill> getBillByOpenedTable(Employee employee, Integer tableNumber, Pageable pageable);

  @Query("select b from Bill b where b.openBillTime >= ?1 and b.openBillTime <= ?2 and b.employee = ?3")
  List<Bill> findBillsByPeriodAndEmployee(LocalDateTime startDateTime, LocalDateTime endDateTime,
      Employee employee);
}
