package cabare.data;

import cabare.entity.model.Cabare;
import cabare.entity.model.Dish;

import cabare.entity.model.DishCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

  @Query("select d from Dish d where d.id = ?1 and d.startDay <= ?2 and d.endDay >= ?2 and d.cabare = ?3")
  Optional<Dish> findByIdAndCabare(Long dishId, int dayOfYear, Cabare cabare);

  @Query("select d from Dish d where d.quantity = 0 and d.startDay <= ?1 and d.endDay >= ?1 and d.cabare = ?2")
  List<Dish> getStopList(int dayOfYear, Cabare cabare);

  @Query("select d from Dish as d where d.dishCategory = ?1")
  Page<Dish> findDishesByDishCategory(DishCategory dishCategory, Pageable pageable);
}
