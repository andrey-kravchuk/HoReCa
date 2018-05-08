package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.Ingredient;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

  Optional<Ingredient> findByIdAndCabare(Long ingredientId, Cabare cabare);

  Page<Ingredient> getAllByCabare(Cabare cabare, Pageable pageable);

}
