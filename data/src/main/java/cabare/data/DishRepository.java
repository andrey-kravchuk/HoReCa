package cabare.data;

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

  Optional<Dish> findById(Long dishId);

  @Query("select d from Dish d where d.quantity = 0 and d.startDay <= ?1 and d.endDay >= ?1")
  List<Dish> getStopList(int dayOfYear);

  @Query ("select d from Dish as d where d.dishCategory = ?1")
  Page<Dish> findDishesByDishCategory (DishCategory dishCategory, Pageable pageable);
}
