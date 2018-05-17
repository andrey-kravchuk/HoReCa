package cabare.service.impl;

import cabare.dto.MeasureDto;
import cabare.entity.model.Measure;
import cabare.exceptions.MeasureNotFoundException;
import cabare.exceptions.MeasureNotSpecifiedException;
import cabare.repository.MeasureRepository;
import cabare.service.MeasureService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureServiceImpl implements MeasureService {

  @Autowired
  MeasureRepository measureRepository;

  @Override
  public Measure findById(Long measureId) {
    if (measureId == null) {
      throw new MeasureNotSpecifiedException();
    }
    Measure measure = measureRepository.findById(measureId)
        .orElseThrow(() -> new MeasureNotFoundException());
    return measure;
  }

  @Override
  public List<MeasureDto> getAll() {
    return measureRepository.findAll().stream()
        .map(measure -> new MeasureDto(measure)).collect(
            Collectors.toList());
  }

//  @Override
//  public void addNewMeasure(String name, String abbreviation) {
//    Measure newMeasure = new Measure();
//    newMeasure.setName(name);
//    newMeasure.setAbbreviation(abbreviation);
//    measureRepository.save(newMeasure);
//  }
//
//  @Override
//  public void updateMeasure(Long measureId, String newMeasureName, String newMeasureAbbreviation) {
//    Measure measure = new Measure();
//    measure.setId(measureId);
//    measure.setName(newMeasureName);
//    measure.setAbbreviation(newMeasureAbbreviation);
//    measureRepository.save(measure);
//  }
}
