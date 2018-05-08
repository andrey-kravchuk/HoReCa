package cabare.controller;


import cabare.service.impl.ZoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/zone")
public class ZoneController {

  @Autowired
  ZoneServiceImpl zoneServiceImpl;

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void addNewZone(@RequestParam("name") String name) {
    zoneServiceImpl.addNewZone(name);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void updateZone(@RequestParam("id") Long zoneId,
      @RequestParam("name") String name) {
    zoneServiceImpl.updateZone(zoneId, name);
  }
}
