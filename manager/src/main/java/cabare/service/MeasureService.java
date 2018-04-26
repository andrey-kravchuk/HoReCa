package cabare.service;

import cabare.entity.model.Measure;
import java.util.List;

public interface MeasureService {

  Measure findById(Long measureId);

  List<Measure> getAll();

  void addNewMeasure(String name, String abriviation);

}
