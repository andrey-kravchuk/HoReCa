package cabare.controller;

import cabare.dto.DishDto;
import cabare.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/waiter/dish")
public class DishController {

  @Autowired
  private DishService dishService;

  @RequestMapping(value = "/by_category")
  public List<DishDto> getDishes(@NotNull(message = "category_id should be specified")
  @Min(value = 1, message = "category_id cannot be less than 1")
  @RequestParam(name = "category_id") Long dishCategoryId
      , Pageable pageable) {
    return dishService.getDishesByCategory(dishCategoryId, pageable);
  }

  @RequestMapping(value = "/stoplist")
  public List<DishDto> getStopList() {
    return dishService.getStopList();
  }
}
