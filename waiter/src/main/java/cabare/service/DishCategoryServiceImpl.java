package cabare.service;

import cabare.data.DishCategoryRepository;
import cabare.dto.DishCategoryDto;
import cabare.entity.model.Cabare;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Employee;
import cabare.entity.model.Zone;
import cabare.exception.DishCategoryNotFoundException;
import cabare.exception.DishCategoryNotSpecifiedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

  @Autowired
  private DishCategoryRepository dishCategoryRepository;
  @Autowired
  private SecurityService securityService;
  @Autowired
  private ZoneService zoneService;


  @Transactional
  @Override
  public List<DishCategoryDto> getDishCategories() {
    Cabare cabare = getCabare();
    return dishCategoryRepository.getAll(cabare).stream()
        .map(dishCategory -> new DishCategoryDto(dishCategory))
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public DishCategory findById(Long dishCategoryId) {
    if (dishCategoryId == null) {
      throw new DishCategoryNotSpecifiedException();
    }
    Cabare cabare = getCabare();
    return dishCategoryRepository.findByIdAndCabare(dishCategoryId, cabare)
        .orElseThrow(() -> new DishCategoryNotFoundException());
  }

  private Cabare getCabare() {
    Employee employee = securityService.getEmployeeFromSession();
    return employee.getCabare();
  }

  @Transactional
  @Override
  public List<DishCategoryDto> getCategotiesByZone(Long zoneId) {
    Zone zone = zoneService.getZoneById(zoneId);
    Cabare cabare = getCabare();
    return dishCategoryRepository.getAllByZone(zone, cabare).stream()
        .map(dishCategory -> new DishCategoryDto(dishCategory))
        .collect(Collectors.toList());
  }
}
