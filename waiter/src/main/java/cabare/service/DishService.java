package cabare.service;

import cabare.dto.DishDto;
import cabare.entity.model.Dish;

import java.util.List;

public interface DishService {

  Dish findByid(Long dishId);

  void addDish(DishDto dishDto);

  void updateDish(DishDto dishDto);

  List<DishDto> getDishByCategory(Long dishCategoryId);

  List<DishDto> getStopList();
}
