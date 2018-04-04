package cabare.service;

import cabare.data.DishRepository;
import cabare.dto.DishDto;
import cabare.entity.domain.Money;
import cabare.entity.model.Dish;
import cabare.entity.model.DishCategory;
import cabare.exception.DishNotFoundException;
import cabare.exception.DishNotSpecifiedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    private int page;
    private int size;

    @Override
    public Dish findByid(Long dishId) {
        if (dishId == null) {
            throw new DishNotSpecifiedException();
        }
        return dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException());
    }

    @Override
    public void addDish(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setPhoto(dishDto.getPhoto());
        dish.setDishOut(dishDto.getDishOut());
        dish.setStartDay(dishDto.getStartDay());
        dish.setEndDay(dishDto.getEndDay());
        dish.setPrice(new Money(dishDto.getPrice()));
        dish.setArchived(false);
        dish.setDishCategory(dishCategoryServices.findById(dishDto.getCategoryId()));

        dishRepository.save(dish);
    }

    @Override
    public void updateDish(DishDto dishDto) {
        Dish dish = new Dish();
        if (dishDto.getName() != null) {
            dish.setName(dishDto.getName());
        }
        if (dishDto.getPhoto() != null) {
            dish.setPhoto(dishDto.getPhoto());
        }
        if (dishDto.getDishOut() != null) {
            dish.setDishOut(dishDto.getDishOut());
        }
        if (dishDto.getStartDay() != null) {
            dish.setStartDay(dishDto.getStartDay());
        }
        if (dishDto.getEndDay() != null) {
            dish.setEndDay(dishDto.getEndDay());
        }
        if (dishDto.getPrice() != null) {
            dish.setPrice(new Money(dishDto.getPrice()));
        }
        if (dishDto.getQuantity() != null) {
            dish.setQuantity(dishDto.getQuantity());
        }
        DishCategory dishCategory = dishCategoryServices.findById(dishDto.getCategoryId());
        if (dishCategory != null) {
            dish.setDishCategory(dishCategory);
        }

        dishRepository.save(dish);
    }

    @Override
    public List<DishDto> getDishByCategory(Long dishCategoryId) {
        DishCategory dishCategory = dishCategoryServices.findById(dishCategoryId);
        Pageable pageable = new PageRequest(page,size);
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
