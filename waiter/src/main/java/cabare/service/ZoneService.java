package cabare.service;

import cabare.dto.ZoneDto;
import cabare.entity.model.Zone;

import java.util.List;

public interface ZoneService {

    ZoneDto getZoneById(Long zoneId);

    List<ZoneDto> getZones();
}
