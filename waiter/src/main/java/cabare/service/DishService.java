package cabare.service;

import cabare.entity.model.Dish;
import cabare.exception.DishNotFoundException;
import cabare.exception.DishNotSpecifiedException;
import cabare.repository.DishRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService {

  @Autowired
  private DishRepository dishRepository;

  public Dish findByid(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    return dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException());
  }
}
