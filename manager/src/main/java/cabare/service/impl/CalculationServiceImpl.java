package cabare.service.impl;

import cabare.dto.CalculationDto;
import cabare.entity.model.Calculation;
import cabare.entity.model.Employee;
import cabare.exceptions.CalculationNotFoundException;
import cabare.exceptions.DishNotFoundException;
import cabare.exceptions.DishNotSpecifiedException;
import cabare.exceptions.IngredintNotFoundException;
import cabare.repository.CalculationRepository;
import cabare.repository.DishRepository;
import cabare.repository.IngredientRepository;
import cabare.service.CalculationService;
import cabare.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

  @Autowired
  CalculationRepository calculationRepository;

  @Autowired
  SecurityService securityService;

  @Autowired
  DishRepository dishRepository;

  @Autowired
  IngredientRepository ingredientRepository;

  @Override
  public CalculationDto findByDishId(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    } else {
      Employee employee = securityService.getEmployeeFromSession();
      return new CalculationDto(calculationRepository.findActualByDish(
          dishRepository.findByIdAndCabare(dishId, employee.getCabare())
              .orElseThrow(() -> new DishNotFoundException()),
          employee.getCabare())
          .orElseThrow(() -> new CalculationNotFoundException()));
    }
  }

  @Override
  public void addCalculation(CalculationDto calculationDto) {
    Employee employee = securityService.getEmployeeFromSession();
    Calculation calculation = new Calculation();
    Boolean isNotArchived = false;
    calculation.setNumber(calculationDto.getNumber());
    calculation.setDate(calculationDto.getDate());
    calculation.setCabare(employee.getCabare());

    calculation.setDish(dishRepository.findByIdAndCabare(calculationDto.getDishId(),
        employee.getCabare()).orElseThrow(() -> new DishNotFoundException()));

    calculation.setIngredient(ingredientRepository.findByIdAndCabare(calculationDto
        .getIngredientId(), employee.getCabare())
        .orElseThrow(() -> new IngredintNotFoundException()));

    calculation.setQuantity(calculationDto.getQuantity());
    calculation.setArchived(isNotArchived);
    calculationRepository.save(calculation);
  }

  @Override
  public void updateCalculation(CalculationDto calculationDto) {
    Employee employee = securityService.getEmployeeFromSession();
    Boolean isNotArchived = false;
    Boolean isArchived = true;

    Calculation calculation = calculationRepository
        .findActualByDish(dishRepository.findByIdAndCabare(calculationDto.getDishId(),
            employee.getCabare()).orElseThrow(() -> new DishNotFoundException()),
            employee.getCabare())
        .orElseThrow(() -> new CalculationNotFoundException());

    calculation.setArchived(isArchived);

    Calculation newCalculation = new Calculation();
    newCalculation.setNumber(calculationDto.getNumber());
    newCalculation.setDate(calculationDto.getDate());
    newCalculation.setCabare(employee.getCabare());

    newCalculation.setDish(dishRepository.findByIdAndCabare(
        calculationDto.getDishId(), employee.getCabare())
        .orElseThrow(() -> new DishNotFoundException()));

    newCalculation.setIngredient(ingredientRepository.findByIdAndCabare(
        calculationDto.getIngredientId(), employee.getCabare())
        .orElseThrow(() -> new IngredintNotFoundException()));

    newCalculation.setQuantity(calculationDto.getQuantity());
    newCalculation.setArchived(isNotArchived);
    calculationRepository.save(newCalculation);
  }
}
