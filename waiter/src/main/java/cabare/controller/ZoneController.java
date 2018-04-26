package cabare.controller;


import cabare.dto.ZoneDto;
import cabare.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waiter/zone")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @RequestMapping(value = "/all")
    public List<ZoneDto> getZones(){
        return zoneService.getZones();
    }

}
