package by.restaurant.repository;

import by.restaurant.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Pavel on 11.04.2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByEmail(String email);
}
