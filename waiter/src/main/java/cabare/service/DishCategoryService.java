package cabare.service;

import cabare.dto.DishCategoryDto;

import java.util.List;

public interface DishCategoryService {

  List<DishCategoryDto> getDishCategories();

  DishCategoryDto findById(Long dishCategoryId);
}
