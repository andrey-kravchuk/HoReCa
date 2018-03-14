package cabare.data;

import cabare.entity.model.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

  @Transactional
  Bill save(Bill bill);
}
