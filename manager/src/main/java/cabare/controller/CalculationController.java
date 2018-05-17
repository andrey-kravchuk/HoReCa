package cabare.controller;

import cabare.dto.CalculationDto;
import cabare.entity.model.Calculation;
import cabare.service.CalculationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/manager/calculation")
public class CalculationController {

  @Autowired
  CalculationService calculationService;

  @RequestMapping(value = "/by_dish_id", method = RequestMethod.GET)
  public CalculationDto findDishDtoByDishId(@RequestParam("dish_id") Long dishId) {
    Calculation calculation = calculationService.findByDishId(dishId);
    return new CalculationDto(calculation);
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void addCalculation(@RequestBody @Valid CalculationDto calculationDto) {
    calculationService.addCalculation(calculationDto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void updateCalculation(@RequestBody @Valid CalculationDto calculationDto) {
    calculationService.updateCalculation(calculationDto);
  }
}
