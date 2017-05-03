package by.restaurant.repository;

import by.restaurant.model.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pavel on 02.05.2017.
 */
@Repository
public interface ZoneRepository extends CrudRepository<Zone, Long> {

    List<Zone> findAll();
}
