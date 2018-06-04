package cabare.service;

import cabare.dto.DishDto;
import cabare.entity.model.Dish;

public interface DishService {

  Dish findDishById(Long dishId);

  void addDish(DishDto dishDto);

  void updateDish(DishDto dishDto, Long dishId);

  void archive(Long dishId);

  void unarchive(Long dishId);

  void seasonalityOfDish(Long dishId, Integer startDay, Integer endDay);

  void changePrice(Long dishId, String price);
}
