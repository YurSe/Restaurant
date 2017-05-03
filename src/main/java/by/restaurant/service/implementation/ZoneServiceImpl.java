package by.restaurant.service.implementation;

import by.restaurant.model.Zone;
import by.restaurant.repository.ZoneRepository;
import by.restaurant.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pavel on 02.05.2017.
 */
@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public List<Zone> getAll() {
        return zoneRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        zoneRepository.delete(id);
    }

    @Override
    public void save(Zone zone) {
        zoneRepository.save(zone);
    }
}
