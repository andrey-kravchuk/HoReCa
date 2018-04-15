package cabare.service;

import cabare.data.DishCategoryRepository;
import cabare.dto.DishCategoryDto;
import cabare.entity.model.DishCategory;
import cabare.exception.DishCategoryNotSpecifiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

    @Autowired
    private DishCategoryRepository dishCategoryRepository;


    @Override
    public List<DishCategoryDto> getDishCategories() {
        return dishCategoryRepository.getAll().stream()
                .map(dishCategory -> new DishCategoryDto(dishCategory))
                .collect(Collectors.toList());
    }

    @Override
    public DishCategoryDto findById(Long dishCategoryId) {
        if (dishCategoryId == null) {
            throw new DishCategoryNotSpecifiedException();
        }
        DishCategory dishCategory = dishCategoryRepository.findById(dishCategoryId).orElseThrow(() -> new DishCategoryNotSpecifiedException());
        return new DishCategoryDto(dishCategory);
    }
}
