package cabare.service.impl;

import cabare.dto.DishDto;
import cabare.entity.model.Cabare;
import cabare.entity.model.Dish;
import cabare.entity.model.Employee;
import cabare.exceptions.DishNotFoundException;
import cabare.exceptions.DishNotSpecifiedException;
import cabare.repository.DishRepository;
import cabare.service.DishService;
import cabare.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

  @Autowired
  private DishRepository dishRepository;

  @Autowired
  private SecurityService securityService;

  @Override
  public DishDto findDishDtoById(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    return new DishDto(dishRepository.findByIdAndCabare(dishId, cabare)
        .orElseThrow(() -> new DishNotFoundException()));
  }

  @Override
  public Dish findDishById(Long dishId) {
    if (dishId == null){
      throw new DishNotSpecifiedException();
    }
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    Dish dish = dishRepository.findByIdAndCabare(dishId, cabare)
        .orElseThrow(() -> new DishNotFoundException());
    return dish;
  }

}

