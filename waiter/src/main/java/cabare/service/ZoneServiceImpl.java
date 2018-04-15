package cabare.service;

import cabare.data.ZoneRepository;
import cabare.dto.ZoneDto;
import cabare.entity.model.Zone;
import cabare.exception.ZoneNotFoundException;
import cabare.exception.ZoneNotSpecifiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public ZoneDto getZoneById(Long zoneId) {
        if (zoneId == null) {
            throw new ZoneNotSpecifiedException();
        }
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new ZoneNotFoundException());
        return new ZoneDto(zone);
    }

    @Override
    public List<ZoneDto> getZones() {
        return zoneRepository.getAll().stream()
                .map(zone -> new ZoneDto(zone))
                .collect(Collectors.toList());
    }
}
