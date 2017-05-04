package by.restaurant.controller;

import by.restaurant.model.Zone;
import by.restaurant.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Pavel on 02.05.2017.
 */
@Controller
@Scope("session")
public class ZoneContr {

    @Autowired
    private ZoneService zoneService;

    private Zone zone = new Zone();

    public void deleteZone(Long id) {
        zoneService.delete(id);
    }

    public void initZone() {
        zone = new Zone();
    }

    public List<Zone> getZones() {
        return zoneService.getAll();
    }

    public void saveZone() {
        zoneService.save(zone);
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
