package cabare.service;

import cabare.dto.MeasureDto;
import java.util.List;

public interface MeasureService {

  MeasureDto findById(Long measureId);

  List<MeasureDto> getAll();

  void addNewMeasure(String name, String abbreviation);

  void updateMeasure(Long measureId, String newMeasureName, String newMeasureAbbreviation);

}
