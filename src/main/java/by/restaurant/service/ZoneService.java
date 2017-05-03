package by.restaurant.service;

import by.restaurant.model.Zone;

import java.util.List;

/**
 * Created by Pavel on 02.05.2017.
 */
public interface ZoneService {

    List<Zone> getAll();

    void delete(Long id);

    void save(Zone zone);
}
