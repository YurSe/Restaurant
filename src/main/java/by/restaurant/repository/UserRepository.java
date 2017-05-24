package by.restaurant.repository;

import by.restaurant.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.name = :name")
    User getUserByName(@Param("name") String name);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);
}
