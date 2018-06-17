package cabare.repository;

import cabare.entity.model.BillWaiter;
import cabare.entity.model.Cabare;
import cabare.entity.model.Employee;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<BillWaiter, Long> {

  Optional<BillWaiter> findByIdAndCabare(Long billId, Cabare cabare);

  @Query("select b from BillWaiter b where b.opened = true and b.employee = ?1")
  List<BillWaiter> findOpenedByEmployee(Employee employee);

  @Query("select b from BillWaiter b where b.activeShift = true and b.employee = ?1")
  List<BillWaiter> findCurrentShiftBillsByEmployee(Employee employee);

  @Query("select b from BillWaiter b where b.employee = ?1 and b.opened = true and b.tableNumber = ?2")
  Slice<BillWaiter> getBillByOpenedTable(Employee employee, Integer tableNumber, Pageable pageable);

  @Query("select b from BillWaiter b where b.openBillTime >= ?1 and b.openBillTime <= ?2 and b.employee = ?3")
  List<BillWaiter> findBillsByPeriodAndEmployee(LocalDateTime startDateTime, LocalDateTime endDateTime,
      Employee employee);
}
