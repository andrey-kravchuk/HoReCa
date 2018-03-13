package cabare.repository;

import cabare.entity.model.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

  @Transactional
  Bill save(Bill bill);
}
