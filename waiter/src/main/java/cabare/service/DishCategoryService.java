package cabare.service;

import cabare.dto.DishCategoryDto;

import java.util.List;

public interface DishCategoryService {


    void addDishCategory(DishCategoryDto dishCategoryDto);

    void updateDishCategory(DishCategoryDto dishCategoryDto);

    List<DishCategoryDto> getDishCategories();
}
