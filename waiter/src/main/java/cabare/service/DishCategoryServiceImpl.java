package cabare.service;

import cabare.data.DishCategoryRepository;
import cabare.dto.DishCategoryDto;
import cabare.entity.model.Cabare;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Employee;
import cabare.exception.DishCategoryNotSpecifiedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

  @Autowired
  private DishCategoryRepository dishCategoryRepository;
  @Autowired
  private SecurityService securityService;


  @Override
  public List<DishCategoryDto> getDishCategories() {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    return dishCategoryRepository.getAll(cabare).stream()
        .map(dishCategory -> new DishCategoryDto(dishCategory))
        .collect(Collectors.toList());
  }

  @Override
  public DishCategoryDto findById(Long dishCategoryId) {
    if (dishCategoryId == null) {
      throw new DishCategoryNotSpecifiedException();
    }
    DishCategory dishCategory = dishCategoryRepository.findById(dishCategoryId)
        .orElseThrow(() -> new DishCategoryNotSpecifiedException());
    return new DishCategoryDto(dishCategory);
  }
}
