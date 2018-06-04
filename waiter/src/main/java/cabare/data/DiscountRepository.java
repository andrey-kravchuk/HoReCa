package cabare.data;

import cabare.entity.model.Cabare;
import cabare.entity.model.Discount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

  Optional<Discount> findByIdAndCabare(Long discountId, Cabare cabare);
}
