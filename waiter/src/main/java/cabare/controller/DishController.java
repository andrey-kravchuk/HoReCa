package cabare.controller;

import cabare.dto.DishDto;
import cabare.service.DishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

  @Autowired
  private DishService dishService;

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void add(@RequestBody DishDto dishDto) {
    dishService.addDish(dishDto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void update(@RequestBody DishDto dishDto) {
    dishService.updateDish(dishDto);
  }

  @RequestMapping(value = "/by_category")
  public List<DishDto> getDishes(@RequestParam(name = "dish_category_id") Long dishCategoryId, @RequestBody Pageable pageable) {
    return dishService.getDishesByCategory(dishCategoryId, pageable);
  }

  @RequestMapping(value = "/stoplist")
  public List<DishDto> getStopList() {
    return dishService.getStopList();
  }
}
