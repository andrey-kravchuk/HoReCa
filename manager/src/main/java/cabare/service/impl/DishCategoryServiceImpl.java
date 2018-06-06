package cabare.service.impl;

import cabare.dto.DishCategoryDto;
import cabare.entity.model.Cabare;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Employee;
import cabare.entity.model.Zone;
import cabare.exceptions.DishCategoryNotFoundException;
import cabare.exceptions.DishCategoryNotSpecifiedException;
import cabare.repository.DishCategoryRepository;
import cabare.service.DishCategoryService;
import cabare.service.SecurityService;
import cabare.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

  @Autowired
  private DishCategoryRepository dishCategoryRepository;

  @Autowired
  private ZoneService zoneService;

  @Autowired
  private SecurityService securityService;

  @Override
  public void addDishCategory(DishCategoryDto dishCategoryDto) {
    DishCategory dishCategory = new DishCategory();
    dishCategory.setName(dishCategoryDto.getName());
    dishCategory.setPhoto(dishCategoryDto.getPhoto());
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    Zone zone = zoneService.findByIdAndCabare(dishCategoryDto.getZoneId(), cabare);
    dishCategory.setZone(zone);
    dishCategory.setCabare(cabare);
    dishCategoryRepository.save(dishCategory);
  }

  @Override
  public void updateDishCategory(DishCategoryDto dishCategoryDto) {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    DishCategory dishCategory = dishCategoryRepository
        .findByIdAndCabare(dishCategoryDto.getId(), cabare).get();
    dishCategory.setName(dishCategoryDto.getName());
    dishCategory.setPhoto(dishCategoryDto.getPhoto());
    Zone zone = zoneService.findByIdAndCabare(dishCategoryDto.getZoneId(), cabare);
    dishCategory.setZone(zone);
    dishCategory.setCabare(cabare);
    dishCategoryRepository.save(dishCategory);
  }

  @Override
  public DishCategory findByIdAndCabare(Long dishCategoryId, Cabare cabare) {
    if (dishCategoryId == null) {
      throw new DishCategoryNotSpecifiedException();
    }
    return dishCategoryRepository.findByIdAndCabare(dishCategoryId, cabare)
        .orElseThrow(() -> new DishCategoryNotFoundException());
  }
}



