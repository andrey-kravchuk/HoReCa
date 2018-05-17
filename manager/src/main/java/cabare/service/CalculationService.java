package cabare.service;

import cabare.dto.CalculationDto;
import cabare.entity.model.Calculation;

public interface CalculationService {

  Calculation findByDishId(Long dishId);

  void addCalculation(CalculationDto calculationDto);

  void updateCalculation(CalculationDto calculationDto);

}
