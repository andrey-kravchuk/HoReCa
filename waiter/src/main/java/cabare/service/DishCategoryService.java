package cabare.service;

import cabare.dto.DishCategoryDto;
import cabare.entity.model.DishCategory;

import java.util.List;

public interface DishCategoryService {

  List<DishCategoryDto> getDishCategories();

  DishCategory findById(Long dishCategoryId);

  List<DishCategoryDto> getCategotiesByZone(Long zoneId);
}
