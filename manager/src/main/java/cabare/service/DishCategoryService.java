package cabare.service;

import cabare.dto.DishCategoryDto;
import cabare.entity.model.Cabare;
import cabare.entity.model.DishCategory;

public interface DishCategoryService {

  void addDishCategory(DishCategoryDto dishCategoryDto);

  void updateDishCategory(DishCategoryDto dishCategoryDto);

  DishCategory findByIdAndCabare (Long dishCategoryId, Cabare cabare);
}



