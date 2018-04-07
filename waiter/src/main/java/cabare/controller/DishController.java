package cabare.controller;

import cabare.dto.DishDto;
import cabare.service.DishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waiter/dish")
public class DishController {

  @Autowired
  private DishService dishService;

  @RequestMapping(value = "/by_category")
  public List<DishDto> getDishes(
      @RequestParam(name = "dish_category_id") Long dishCategoryId
      , Pageable pageable) {
    return dishService.getDishesByCategory(dishCategoryId, pageable);
  }

  @RequestMapping(value = "/stoplist")
  public List<DishDto> getStopList() {
    return dishService.getStopList();
  }
}
