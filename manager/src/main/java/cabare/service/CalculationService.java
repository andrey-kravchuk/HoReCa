package cabare.service;

import cabare.dto.CalculationDto;
import cabare.entity.model.Calculation;
import java.util.List;

public interface CalculationService {



  List<Calculation> getRecipe(Long dishId);

  void addRecipe(Long dishId, List<CalculationDto> recipe);

  void updateRecipe(Long dishId, List<CalculationDto> recipe);



}
