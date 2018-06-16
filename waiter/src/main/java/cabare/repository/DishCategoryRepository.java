package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.DishCategoryWaiter;
import cabare.entity.model.Zone;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DishCategoryRepository extends JpaRepository<DishCategoryWaiter, Long> {

  Optional<DishCategoryWaiter> findByIdAndCabare(Long dishCategoryId, Cabare cabare);

  @Query("select d from DishCategoryWaiter d where d.cabare = ?1")
  List<DishCategoryWaiter> getAll(Cabare cabare);

  @Query("select d from DishCategoryWaiter d where d.zone = ?1 and d.cabare = ?2")
  List<DishCategoryWaiter> getAllByZone(Zone zone, Cabare cabare);

}
