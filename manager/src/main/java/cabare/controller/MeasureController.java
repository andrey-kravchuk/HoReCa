package cabare.controller;

import cabare.dto.MeasureDto;
import cabare.service.impl.MeasureServiceImpl;
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
  MeasureServiceImpl measureServiceImpl;

  @RequestMapping(value = "/by_id", method = RequestMethod.GET)
  public MeasureDto findById(@RequestParam("id") Long measureDtoId) {
    return measureServiceImpl.findById(measureDtoId);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<MeasureDto> getAll() {
    return measureServiceImpl.getAll();
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
