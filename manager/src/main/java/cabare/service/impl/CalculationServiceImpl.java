package cabare.service.impl;

import cabare.dto.CalculationDto;
import cabare.repository.CalculationRepository;
import cabare.repository.DishRepository;
import cabare.repository.IngredientRepository;
import cabare.service.CalculationService;
import cabare.service.SecurityService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService{

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
    return null;
  }

  @Override
  public void addCalculation(Long number, Date date, Long dishId, Long ingredientId,
      Double quantity) {

  }

  @Override
  public void updateCalculation(Long calculationId, Long number, Date date, Long dishId,
      Long ingredientId, Double quantity) {

  }
}
