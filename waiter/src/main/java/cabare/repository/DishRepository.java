package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.DishWaiter;
import cabare.entity.model.DishCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<DishWaiter, Long> {

  @Query("select d from DishWaiter d where d.id = ?1 and d.startDay <= ?2 and d.endDay >= ?2 and d.cabare = ?3")
  Optional<DishWaiter> findByIdAndCabare(Long dishId, int dayOfYear, Cabare cabare);

  @Query("select d from DishWaiter d where d.quantity = 0 and d.startDay <= ?1 and d.endDay >= ?1 and d.cabare = ?2")
  List<DishWaiter> getStopList(int dayOfYear, Cabare cabare);

  @Query("select d from DishWaiter as d where d.dishCategory = ?1 and d.startDay <= ?2 and d.endDay >= ?2")
  Page<DishWaiter> findDishesByDishCategory(DishCategory dishCategory, int dayOfYear, Pageable pageable);
}
