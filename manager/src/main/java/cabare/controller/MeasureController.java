package cabare.controller;

import cabare.dto.MeasureDto;
import cabare.entity.model.Measure;
import cabare.service.MeasureService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/measure")
public class MeasureController {

  @Autowired
  MeasureService measureService;

  @RequestMapping(value = "/by_id", method = RequestMethod.GET)
  public MeasureDto findById(@RequestParam("id") Long measureDtoId) {
    Measure measure = measureService.findById(measureDtoId);
    return new MeasureDto(measure);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<MeasureDto> getAll() {
    return measureService.getAll();
  }

//  @RequestMapping(value = "/add", method = RequestMethod.PUT)
//  public void addMeasure(@RequestParam("name") String name,
//      @RequestParam("abbreviation") String abbreviation) {
//    measureServiceImpl.addNewMeasure(name, abbreviation);
//  }
//
//  @RequestMapping(value = "/update", method = RequestMethod.POST)
//  public void updateMeasure(@RequestParam("id") Long id,
//      @RequestParam("name") String name,
//      @RequestParam("abbreviation") String abbreviation) {
//    measureServiceImpl.updateMeasure(id, name, abbreviation);
//  }
}
