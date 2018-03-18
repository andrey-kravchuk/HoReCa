package cabare.data;

import cabare.entity.model.Dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

  Optional<Dish> findById(Long dishId);
}
