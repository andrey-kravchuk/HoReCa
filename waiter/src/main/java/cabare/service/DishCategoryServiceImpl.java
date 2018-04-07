package cabare.service;

import cabare.dto.DishCategoryDto;
import cabare.entity.model.DishCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

    @Override
    public void addDishCategory(DishCategoryDto dishCategoryDto) {

    }

    @Override
    public void updateDishCategory(DishCategoryDto dishCategoryDto) {

    }

    @Override
    public List<DishCategoryDto> getDishCategories() {
        return null;
    }

    @Override
    public DishCategory findById(Long dishCategoryId) {
        return null;
    }
}
