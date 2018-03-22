package cabare.service;

import cabare.data.DishRepository;
import cabare.dto.DishDto;
import cabare.entity.model.Dish;
import cabare.exception.DishNotFoundException;
import cabare.exception.DishNotSpecifiedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

  @Autowired
  private DishRepository dishRepository;
  @Autowired
  private TimeService timeService;

  @Override
  public Dish findByid(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    return dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException());
  }

  @Override
  public void addDish(DishDto dishDto) {

  }

  @Override
  public void updateDish(DishDto dishDto) {

  }

  @Override
  public List<DishDto> getDishByCategory(Long dishCategoryId) {
    return null;
  }

  @Override
  public List<DishDto> getStopList() {
    int dayOfYear = timeService.getCurrentDate().getDayOfYear();
    return dishRepository.getStopList(dayOfYear).stream()
        .map(dish -> new DishDto(dish))
        .collect(Collectors.toList());
  }
}
