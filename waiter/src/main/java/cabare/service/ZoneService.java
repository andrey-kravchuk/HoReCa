package cabare.service;

import cabare.dto.ZoneDto;
import cabare.entity.model.Zone;

import java.util.List;

public interface ZoneService {

  Zone getZoneById(Long zoneId);

  List<ZoneDto> getZones();
}
