package cabare.service;

import cabare.dto.CalculationDto;
import java.util.Date;

public interface CalculationService {

  CalculationDto findByDishId(Long dishId);

  void addCalculation(CalculationDto calculationDto);

  void updateCalculation(CalculationDto calculationDto);

}
