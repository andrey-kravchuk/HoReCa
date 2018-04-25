package cabare.service.impl;

import cabare.dto.DishCategoryDto;
import cabare.entity.model.Cabare;
import cabare.entity.model.DishCategory;
import cabare.entity.model.Zone;
import cabare.repository.CabareRepository;
import cabare.repository.DishCategoryRepository;
import cabare.repository.ZoneRepository;
import cabare.service.DishCategoryService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

  @Autowired
  private DishCategoryRepository dishCategoryRepository;

  @Autowired
  private ZoneRepository zoneRepository;

  @Autowired
  private CabareRepository cabareRepository;

  @Transactional
  @Override
  public void addDishCategory (DishCategoryDto dishCategoryDto) {
    DishCategory dishCategory = new DishCategory();
    dishCategory.setId(dishCategoryDto.getId());
    dishCategory.setName(dishCategoryDto.getName());
    dishCategory.setPhoto(dishCategoryDto.getPhoto());
    Cabare cabare = cabareRepository.findById(dishCategoryDto.getCabareId()).get();
    Zone zone = zoneRepository.findZoneByIdAndCabare(dishCategoryDto.getZoneId(), cabare).get();
    dishCategory.setZone(zone);
    dishCategory.setCabare(cabare);
    dishCategoryRepository.save(dishCategory);
  }
}


