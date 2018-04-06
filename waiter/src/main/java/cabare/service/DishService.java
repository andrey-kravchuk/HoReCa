package cabare.service;

import cabare.dto.DishDto;
import cabare.entity.model.Dish;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DishService {

  Dish findByid(Long dishId);

  void addDish(DishDto dishDto);

  void updateDish(DishDto dishDto);

  List<DishDto> getDishesByCategory(Long dishCategoryId, Pageable pageable);

  List<DishDto> getStopList();
}
