package cabare.service.impl;

import cabare.dto.CalculationDto;
import cabare.entity.model.Calculation;
import cabare.entity.model.Dish;
import cabare.entity.model.Ingredient;
import cabare.repository.CalculationRepository;
import cabare.service.CalculationService;
import cabare.service.DishService;
import cabare.service.IngredientService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalculationServiceImpl implements CalculationService {

  @Autowired
  CalculationRepository calculationRepository;

  @Autowired
  DishService dishService;

  @Autowired
  IngredientService ingredientService;

  @Override
  public List<Calculation> getRecipe(Long dishId) {
    Dish dish = dishService.findDishById(dishId);
    List<Calculation> recipe = calculationRepository.findActualByDish(dish);
    return recipe;
  }

  @Transactional
  @Override
  public void addRecipe(Long dishId, List<CalculationDto> recipe) {
    Dish dish = dishService.findDishById(dishId);
    List<Calculation> newRecipe = new ArrayList<>();
    for (CalculationDto c : recipe) {
      Calculation calculation = new Calculation();
      calculation.setDish(dish);
      Long ingredientId = c.getIngredientId();
      Ingredient ingredient = ingredientService.findIngredientById(ingredientId);
      calculation.setIngredient(ingredient);
      Double quantity = c.getQuantity();
      calculation.setQuantity(quantity);
      newRecipe.add(calculation);
    }
    calculationRepository.save(newRecipe);
  }

  @Transactional
  @Override
  public void updateRecipe(Long dishId, List<CalculationDto> recipe) {
    List<Calculation> actualRecipe = this.getRecipe(dishId);

    for (Calculation ac : actualRecipe) {
      ac.setArchived(true);
    }

    calculationRepository.save(actualRecipe);

    this.addRecipe(dishId, recipe);
  }


}
