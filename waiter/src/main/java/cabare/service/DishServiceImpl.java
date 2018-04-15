package cabare.service;

import cabare.data.DishCategoryRepository;
import cabare.data.DishRepository;
import cabare.dto.DishCategoryDto;
import cabare.dto.DishDto;
import cabare.entity.model.Dish;
import cabare.entity.model.DishCategory;
import cabare.exception.DishNotFoundException;
import cabare.exception.DishNotSpecifiedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

  @Autowired
  private DishRepository dishRepository;
  @Autowired
  private TimeService timeService;
  @Autowired
  private DishCategoryService dishCategoryServices;
  @Autowired
  private DishCategoryRepository dishCategoryRepository;

  @Override
  public Dish findByid(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    return dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException());
  }

  @Override
  public List<DishDto> getDishesByCategory(Long dishCategoryId, Pageable pageable) {
    DishCategoryDto dishCategoryDto = dishCategoryServices.findById(dishCategoryId);
    Optional<DishCategory> optionalDishCategory = dishCategoryRepository.findById(dishCategoryDto.getId());
    DishCategory dishCategory = optionalDishCategory.get();
    return dishRepository.findDishesByDishCategory(dishCategory, pageable).getContent().stream()
        .map(dish -> new DishDto(dish))
        .collect(Collectors.toList());
  }

  @Override
  public List<DishDto> getStopList() {
    int dayOfYear = timeService.getCurrentDate().getDayOfYear();
    return dishRepository.getStopList(dayOfYear).stream()
        .map(dish -> new DishDto(dish))
        .collect(Collectors.toList());
  }
}
