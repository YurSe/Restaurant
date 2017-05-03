package by.restaurant.controller;

import by.restaurant.model.Zone;
import by.restaurant.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 02.05.2017.
 */
@Controller
@Scope("session")
public class ZoneContr {

    @Autowired
    private ZoneService zoneService;

    private List<Zone> zones;

    private Zone zone;

    @PostConstruct
    public void init() {
        zone = new Zone();
        zones = zoneService.getAll();
        if (zones == null) {
            zones = new ArrayList<>();
        }
    }

    public void deleteZone(Long id) {
        zoneService.delete(id);
    }

    public void initZone() {
        zone = new Zone();
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void saveZone() {
        zoneService.save(zone);
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
