package cabare.service.impl;

import cabare.entity.model.Cabare;
import cabare.entity.model.Zone;
import cabare.exceptions.ZoneNotFoundException;
import cabare.exceptions.ZoneNotSpecifiedException;
import cabare.repository.CabareRepository;
import cabare.repository.ZoneRepository;
import cabare.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneServiceImpl implements ZoneService {

  @Autowired
  private ZoneRepository zoneRepository;

  @Autowired
  private CabareRepository cabareRepository;

  @Override
  public void addNewZone(String zoneName) {
    if (zoneName == null) {
      throw new ZoneNotSpecifiedException();
    } else {
      Zone newZone = new Zone();
      Cabare cabare = cabareRepository.findById(1L).get();
      newZone.setCabare(cabare);
      newZone.setName(zoneName);
      zoneRepository.save(newZone);
    }
  }

  @Override
  public void updateZone(Long zoneId, String zoneUpdateName) {
    if (zoneId == null){
      throw new ZoneNotSpecifiedException();
    } else {
      Cabare cabare = cabareRepository.findById(1L).get();
      Zone updateZone = zoneRepository.findByIdAndCabare(zoneId,cabare)
          .orElseThrow(() -> new ZoneNotFoundException());
      updateZone.setName(zoneUpdateName);
      zoneRepository.save(updateZone);
    }
  }
}
