package cabare.service;

import cabare.dto.DishCategoryDto;
import cabare.entity.model.DishCategoryWaiter;

import java.util.List;

public interface DishCategoryService {

  List<DishCategoryDto> getDishCategories();

  DishCategoryWaiter findById(Long dishCategoryId);

  List<DishCategoryDto> getCategotiesByZone(Long zoneId);
}
