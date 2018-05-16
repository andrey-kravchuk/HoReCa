package cabare.service.impl;

import cabare.dto.CalculationDto;
import cabare.entity.model.Calculation;
import cabare.entity.model.Dish;
import cabare.exceptions.CalculationNotFoundException;
import cabare.exceptions.DishNotSpecifiedException;
import cabare.repository.CalculationRepository;
import cabare.service.CalculationService;
import cabare.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

  @Autowired
  CalculationRepository calculationRepository;

  @Autowired
  DishServiceImpl dishServiceImpl;

  @Autowired
  IngredientServiceImpl ingredientServiceImpl;

  @Autowired
  SecurityService securityService;

  @Override
  public CalculationDto findByDishId(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    Dish dish = dishServiceImpl.findDishById(dishId);
    Calculation calculation = calculationRepository.findActualByDish(dish)
        .orElseThrow(() -> new CalculationNotFoundException());
    CalculationDto calculationDto = new CalculationDto(calculation);
    return calculationDto;
  }


  @Override
  public void addCalculation(CalculationDto calculationDto) {
    Calculation calculation = new Calculation();
    calculation.setNumber(calculationDto.getNumber());
    calculation.setDate(calculationDto.getDate());
    calculation.setDish(dishServiceImpl.findDishById(calculationDto.getDishId()));
    calculation.setIngredient(ingredientServiceImpl
        .findIngredientById(calculationDto.getIngredientId()));
    calculation.setQuantity(calculationDto.getQuantity());
    calculationRepository.save(calculation);
  }

  @Override
  public void updateCalculation(CalculationDto calculationDto) {
    Boolean isArchived = true;
    Calculation calculation = calculationRepository.findActualByDish(dishServiceImpl
        .findDishById(calculationDto.getDishId()))
        .orElseThrow((() -> new CalculationNotFoundException()));
    calculation.setArchived(isArchived);
    calculationRepository.save(calculation);

    Calculation newCalculation = new Calculation();
    newCalculation.setNumber(calculationDto.getNumber());
    newCalculation.setDate(calculationDto.getDate());
    newCalculation.setDish(dishServiceImpl.findDishById(calculationDto.getDishId()));
    newCalculation.setIngredient(ingredientServiceImpl
        .findIngredientById(calculationDto.getIngredientId()));
    newCalculation.setQuantity(calculationDto.getQuantity());
    this.addCalculation(new CalculationDto(newCalculation));
  }
}
