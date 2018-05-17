package cabare.service.impl;

import cabare.dto.CalculationDto;
import cabare.entity.model.Calculation;
import cabare.entity.model.Dish;
import cabare.entity.model.Ingredient;
import cabare.exceptions.CalculationNotFoundException;
import cabare.exceptions.DishNotFoundException;
import cabare.exceptions.DishNotSpecifiedException;
import cabare.repository.CalculationRepository;
import cabare.service.CalculationService;
import cabare.service.DishService;
import cabare.service.IngredientService;
import cabare.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

  @Autowired
  CalculationRepository calculationRepository;

  @Autowired
  DishService dishService;

  @Autowired
  IngredientService ingredientService;

  @Autowired
  SecurityService securityService;

  @Override
  public Calculation findByDishId(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    Dish dish = dishService.findDishById(dishId);
    Calculation calculation = calculationRepository.findActualByDish(dish)
        .orElseThrow(() -> new CalculationNotFoundException());
    return calculation;
  }


  @Override
  public void addCalculation(CalculationDto calculationDto) {
    Calculation calculation = new Calculation();
    calculation.setNumber(calculationDto.getNumber());
    calculation.setDate(calculationDto.getDate());
    Dish dish = dishService.findDishById(calculationDto.getDishId());
    calculation.setDish(dish);
    Ingredient ingredient = ingredientService.findIngredientById(calculationDto.getIngredientId());
    calculation.setIngredient(ingredient);
    calculation.setQuantity(calculationDto.getQuantity());
    calculationRepository.save(calculation);
  }

  @Override
  public void updateCalculation(CalculationDto calculationDto) {
    Calculation calculation = this.findByDishId(calculationDto.getDishId());
    calculation.setArchived(true);
    calculationRepository.save(calculation);

    this.addCalculation(calculationDto);
  }
}
