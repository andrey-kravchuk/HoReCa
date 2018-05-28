package cabare.controller;

import cabare.dto.DishDto;
import cabare.entity.model.Dish;
import cabare.exceptions.ApplicationException;
import cabare.service.DishService;
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
@RequestMapping(value = "/manager/dish")
public class DishController {

  @Autowired
  private DishService dishService;

  @RequestMapping(value = "/by_id", method = RequestMethod.GET)
  public Dish findDishById(@RequestParam(name = "dish_id") Long DishId) {
    return dishService.findDishById(DishId);
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void addDish(@Valid @RequestBody DishDto dishDto) {
    dishService.addDish(dishDto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void updateDish(@Valid @RequestBody DishDto dishDto) throws Exception {
    dishService.updateDish(dishDto);
  }

  @RequestMapping(value = "/archive", method = RequestMethod.POST)
  public void archive(@RequestParam(name = "dish_id") Long DishId) {
    dishService.archive(DishId);
  }

  @RequestMapping(value = "/unarchive", method = RequestMethod.POST)
  public void unarchive(@RequestParam(name = "dish_id") Long DishId) {
    dishService.unarchive(DishId);
  }

  @RequestMapping(value = "seasonality", method = RequestMethod.POST)
  public void seasonalityOfDish(
      @RequestParam(name = "dish_id") Long DishId,
      @RequestParam(name = "start_day") Integer startDay,
      @RequestParam(name = "end_day") Integer endDay) {
    dishService.seasonalityOfDish(DishId, startDay, endDay);
  }

  @RequestMapping(value = "/price", method = RequestMethod.POST)
  public void changePrice(@RequestParam(name = "dish_id") Long DishId, String price) {
    dishService.changePrice(DishId, price);
  }
}