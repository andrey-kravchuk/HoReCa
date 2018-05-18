package cabare.controller;

import cabare.dto.CalculationDto;
import cabare.entity.model.Calculation;
import cabare.service.CalculationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/manager/recipe")
public class CalculationController {

  @Autowired
  CalculationService calculationService;

  @RequestMapping(value = "/by_dish_id", method = RequestMethod.GET)
  public List<CalculationDto> findDishDtoByDishId(@RequestParam("dish_id") Long dishId) {
    List<Calculation> recipe = calculationService.getRecipe(dishId);
    List<CalculationDto> recipeDto = recipe
        .stream()
        .map(calculation -> new CalculationDto(calculation))
        .collect(Collectors.toList());
    return recipeDto;
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void addCalculation(@RequestParam(value = "dish_id") Long dishId,
      @RequestBody List<CalculationDto> newRecipe) {
    calculationService.addRecipe(dishId, newRecipe);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void updateCalculation(@RequestParam(value = "dish_id") Long dishId,
      @RequestBody List<CalculationDto> recipe) {
    calculationService.updateRecipe(dishId, recipe);
  }
}
