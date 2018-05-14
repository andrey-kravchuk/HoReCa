package cabare.repository;

import cabare.dto.CalculationDto;
import cabare.entity.model.Cabare;
import cabare.entity.model.Calculation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {

  @Query("select c from Calculation c where c.dishId = ?1 and c.cabare = ?2 and c.archived = ?3")
  Optional<CalculationDto> getActualCalculationByDishId(Long dishId, Cabare cabare, Boolean b);

}
