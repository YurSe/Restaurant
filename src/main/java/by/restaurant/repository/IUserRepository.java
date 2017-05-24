package by.restaurant.repository;

import by.restaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name = :name")
    User getUserByName(@Param("name") String name);

}
