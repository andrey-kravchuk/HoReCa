package cabare.repository;

import cabare.entity.model.Calculation;
import cabare.entity.model.Dish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {

    @Query("select c from Calculation c where c.archived = false and c.dish = ?1")
    List<Calculation> findActualByDish(Dish dish);

}
