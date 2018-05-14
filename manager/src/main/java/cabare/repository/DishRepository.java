package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.Dish;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {

  @Query("select d from Dish d where d.id = ?1 and d.cabare = ?2")
  Optional<Dish> findByIdAndCabare(Long dishId, Cabare cabare);

}
