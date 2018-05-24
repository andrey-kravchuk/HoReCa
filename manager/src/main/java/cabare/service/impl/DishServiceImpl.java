package cabare.service.impl;

import cabare.dto.DishDto;
import cabare.entity.domain.Money;
import cabare.entity.model.Cabare;
import cabare.entity.model.Dish;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Employee;
import cabare.entity.model.OrderItem;
import cabare.exceptions.DishNotFoundException;
import cabare.exceptions.DishNotSpecifiedException;
import cabare.repository.DishCategoryRepository;
import cabare.repository.DishRepository;
import cabare.repository.OrderItemRepository;
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

  @Autowired
  private DishCategoryRepository dishCategoryRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Override
  public Dish findDishById(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    Dish dish = dishRepository.findByIdAndCabare(dishId, cabare)
        .orElseThrow(() -> new DishNotFoundException());
    return dish;
  }

  @Override
  public void addDish(DishDto dishDto) {
    Dish dish = new Dish();
    dish.setName(dishDto.getName());
    dish.setPhoto(dishDto.getPhoto());
    dish.setDishOut(dishDto.getDishOut());
    dish.setStartDay(dishDto.getStartDay());
    dish.setEndDay(dishDto.getEndDay());

    String value = dishDto.getPrice();
    Money money = new Money(value);
    dish.setPrice(money);

    dish.setArchived(dishDto.getArchived());
    dish.setQuantity(dishDto.getQuantity());

    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    dish.setCabare(cabare);
    DishCategory dishCategory = dishCategoryRepository
        .findByIdAndCabare(dishDto.getCategoryId(), cabare).get();
    dish.setDishCategory(dishCategory);
    dishRepository.save(dish);
  }

  @Override
  public void updateDish(DishDto dishDto) throws Exception {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    Dish dish = dishRepository.findByIdAndCabare(dishDto.getId(), cabare).get();
    OrderItem orderItem = orderItemRepository.findByDish_Id(dish.getId());
    if (orderItem != null) {
      throw new Exception("changing the dish is not possible");
    }
    dish.setName(dishDto.getName());
    dish.setPhoto(dishDto.getPhoto());
    dish.setDishOut(dishDto.getDishOut());
    dish.setStartDay(dishDto.getStartDay());
    dish.setEndDay(dishDto.getEndDay());
    dish.setCabare(cabare);

    String value = dishDto.getPrice();
    Money money = new Money(value);
    dish.setPrice(money);

    dish.setArchived(dishDto.getArchived());
    dish.setQuantity(dishDto.getQuantity());

    DishCategory dishCategory = dishCategoryRepository
        .findByIdAndCabare(dishDto.getCategoryId(), cabare).get();
    dish.setDishCategory(dishCategory);
    dishRepository.save(dish);
  }

  @Override
  public void archive(Long dishId) {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    Dish dish = dishRepository.findByIdAndCabare(dishId, cabare).get();
    dish.setArchived(true);
    dishRepository.save(dish);
  }

  @Override
  public void unarchive(Long dishId) {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    Dish dish = dishRepository.findByIdAndCabare(dishId, cabare).get();
    dish.setArchived(false);
    dishRepository.save(dish);
  }

  @Override
  public void seasonalityOfDish(Long dishId, Integer startDay, Integer endDay) {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    Dish dish = dishRepository.findByIdAndCabare(dishId, cabare).get();
    dish.setStartDay(startDay);
    dish.setEndDay(endDay);
    dishRepository.save(dish);
  }

  @Override
  public void changePrice(Long dishId, String price) {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    Dish dish = dishRepository.findByIdAndCabare(dishId, cabare).get();
    Money money = new Money(price);
    dish.setPrice(money);
    dishRepository.save(dish);
  }
}

