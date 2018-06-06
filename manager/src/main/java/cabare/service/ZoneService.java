package cabare.service;

import cabare.entity.model.Cabare;
import cabare.entity.model.Zone;

public interface ZoneService {

  void addNewZone(String zoneName);

  void updateZone(Long zoneId, String zoneUpdateName);

  Zone findByIdAndCabare(Long zoneId, Cabare cabare);

}
