package cabare.service;

import cabare.dto.DishDto;
import cabare.entity.model.Dish;

public interface DishService {

  DishDto findDishDtoById(Long dishId);

  Dish findDishById(Long dishId);

}
