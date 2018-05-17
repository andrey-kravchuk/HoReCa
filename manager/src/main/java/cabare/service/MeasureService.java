package cabare.service;

import cabare.dto.MeasureDto;
import cabare.entity.model.Measure;
import java.util.List;

public interface MeasureService {

  Measure findById(Long measureId);

  List<MeasureDto> getAll();

//  void addNewMeasure(String name, String abbreviation);
//
//  void updateMeasure(Long measureId, String newMeasureName, String newMeasureAbbreviation);

}
