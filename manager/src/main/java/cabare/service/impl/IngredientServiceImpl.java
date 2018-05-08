package cabare.service.impl;

import cabare.dto.IngredientDto;
import cabare.entity.model.Employee;
import cabare.entity.model.Ingredient;
import cabare.exceptions.IngredientNotSpecifiedException;
import cabare.exceptions.IngredintNotFoundException;
import cabare.exceptions.MeasureNotFoundException;
import cabare.exceptions.MeasureNotSpecifiedException;
import cabare.repository.IngredientRepository;
import cabare.repository.MeasureRepository;
import cabare.service.IngredientService;
import cabare.service.SecurityService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService{

  @Autowired
  IngredientRepository ingredientRepository;

  @Autowired
  SecurityService securityService;

  @Autowired
  MeasureRepository measureRepository;

  @Override
  public IngredientDto findIngredientById(Long ingredientId) {
    if (ingredientId == null){
      throw new IngredientNotSpecifiedException();
    }
    Employee employee = securityService.getEmployeeFromSession();
    return new IngredientDto(ingredientRepository.findByIdAndCabare(ingredientId,employee.getCabare())
        .orElseThrow(() -> new IngredintNotFoundException()));
  }

  @Override
  public List<IngredientDto> getPageOfIngredient(Pageable pageable) {
    Employee employee = securityService.getEmployeeFromSession();
    return ingredientRepository.getAllByCabare(employee.getCabare(),pageable)
        .getContent()
        .stream()
        .map(ingredient -> new IngredientDto(ingredient))
        .collect(Collectors.toList());
  }

  @Override
  public void addNewIngredient(String newIngredientName, Long measureId) {
    if (measureId == null){
      throw new MeasureNotSpecifiedException();
    }
    Employee employee = securityService.getEmployeeFromSession();
    Ingredient newIngredient = new Ingredient();
    newIngredient.setName(newIngredientName);
    newIngredient.setMeasure(measureRepository.findById(measureId)
        .orElseThrow(() -> new MeasureNotFoundException()));
    newIngredient.setCabare(employee.getCabare());
    ingredientRepository.save(newIngredient);

  }

  @Override
  public void updateIngredient(Long ingredientId, String ingredientName, Long measureId) {
    if (measureId == null){
      throw new MeasureNotSpecifiedException();
    }
    if (ingredientId == null){
      throw new IngredientNotSpecifiedException();
    }
    Employee employee = securityService.getEmployeeFromSession();
    Ingredient ingredient = ingredientRepository
        .findByIdAndCabare(ingredientId, employee.getCabare())
        .orElseThrow(() -> new IngredintNotFoundException());
    ingredient.setId(ingredientId);
    ingredient.setName(ingredientName);
    ingredient.setMeasure(measureRepository.findById(measureId)
        .orElseThrow(() -> new MeasureNotFoundException()));
    ingredient.setCabare(employee.getCabare());
    ingredientRepository.save(ingredient);
  }
}
