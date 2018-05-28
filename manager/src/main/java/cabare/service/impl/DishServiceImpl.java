package cabare.service.impl;

import cabare.dto.DishDto;
import cabare.entity.domain.Money;
import cabare.entity.model.Cabare;
import cabare.entity.model.Dish;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Employee;
import cabare.entity.model.OrderItem;
import cabare.exceptions.CabareNotFoundException;
import cabare.exceptions.DishNotFoundException;
import cabare.exceptions.DishNotSpecifiedException;
import cabare.exceptions.DishRuntimeException;
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

  private Cabare getCabare() {
    Employee employee = securityService.getEmployeeFromSession();
    return employee.getCabare();
  }

  private Dish getDish(Long dishId) {
    Cabare cabare = getCabare();
    return dishRepository.findByIdAndCabare(dishId, cabare)
        .orElseThrow(() -> new DishNotFoundException());
  }

  private DishCategory getDishCategory(Long categoryId) {
    return dishCategoryRepository
        .findByIdAndCabare(categoryId, getCabare())
        .orElseThrow(() -> new CabareNotFoundException());
  }

  @Override
  public Dish findDishById(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    return getDish(dishId);
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
    dish.setCabare(getCabare());
    DishCategory dishCategory = getDishCategory(dishDto.getCategoryId());
    dish.setDishCategory(dishCategory);
    dishRepository.save(dish);
  }

  @Override
  public void updateDish(DishDto dishDto) {
    Dish dish = getDish(dishDto.getId());
    OrderItem orderItem = orderItemRepository.findByDish_Id(dish.getId());
    if (orderItem != null) {
      throw new DishRuntimeException("there is a sale for this dish");
    }
    dish.setName(dishDto.getName());
    dish.setPhoto(dishDto.getPhoto());
    dish.setDishOut(dishDto.getDishOut());
    dish.setStartDay(dishDto.getStartDay());
    dish.setEndDay(dishDto.getEndDay());
    dish.setCabare(getCabare());

    String value = dishDto.getPrice();
    Money money = new Money(value);
    dish.setPrice(money);

    dish.setArchived(dishDto.getArchived());
    dish.setQuantity(dishDto.getQuantity());

    DishCategory dishCategory = getDishCategory(dishDto.getCategoryId());
    dish.setDishCategory(dishCategory);
    dishRepository.save(dish);
  }

  @Override
  public void archive(Long dishId) {
    Dish dish = getDish(dishId);
    dish.setArchived(true);
    dishRepository.save(dish);
  }

  @Override
  public void unarchive(Long dishId) {
    Dish dish = getDish(dishId);
    dish.setArchived(false);
    dishRepository.save(dish);
  }

  @Override
  public void seasonalityOfDish(Long dishId, Integer startDay, Integer endDay) {
    Dish dish = getDish(dishId);
    dish.setStartDay(startDay);
    dish.setEndDay(endDay);
    dishRepository.save(dish);
  }

  @Override
  public void changePrice(Long dishId, String price) {
    Dish dish = getDish(dishId);
    Money money = new Money(price);
    dish.setPrice(money);
    dishRepository.save(dish);
  }
}

