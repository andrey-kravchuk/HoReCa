package cabare.service;

import cabare.dto.DishDto;
import cabare.entity.model.Dish;

public interface DishService {

  Dish findDishById(Long dishId);

}
