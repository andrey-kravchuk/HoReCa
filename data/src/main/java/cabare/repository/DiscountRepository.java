package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.Discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

  Optional<Discount> findByIdAndCabare(Long discountId, Cabare cabare);
}
