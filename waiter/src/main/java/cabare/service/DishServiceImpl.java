package cabare.service;

import cabare.data.DishRepository;
import cabare.dto.DishDto;
import cabare.entity.model.Cabare;
import cabare.entity.model.Dish;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Employee;
import cabare.exception.DishCategoryNotSpecifiedException;
import cabare.exception.DishNotFoundException;
import cabare.exception.DishNotSpecifiedException;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
  private SecurityService securityService;

  @Override
  public Dish findByid(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    LocalDate date = LocalDate.now();
    int day = date.getDayOfYear();
    return dishRepository.findByIdAndCabare(dishId, day, cabare)
        .orElseThrow(() -> new DishNotFoundException());
  }

  @Override
  public List<DishDto> getDishesByCategory(Long dishCategoryId, Pageable pageable) {
    if (dishCategoryId == null) {
      throw new DishCategoryNotSpecifiedException();
    }
    DishCategory dishCategory = dishCategoryServices.findById(dishCategoryId);
    LocalDate date = LocalDate.now();
    int day = date.getDayOfYear();
    return dishRepository.findDishesByDishCategory(dishCategory, day, pageable).getContent()
        .stream()
        .map(dish -> new DishDto(dish))
        .collect(Collectors.toList());

  }

  @Override
  public List<DishDto> getStopList() {
    int dayOfYear = timeService.getCurrentDate().getDayOfYear();
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    return dishRepository.getStopList(dayOfYear, cabare).stream()
        .map(dish -> new DishDto(dish))
        .collect(Collectors.toList());
  }
}
