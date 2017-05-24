package by.restaurant.service;

import by.restaurant.model.User;
import java.util.List;

public interface IUserService {

    User getById(Long id);

    User save(final User order);

    void delete(Long id);

    List<User> getAll();

    User getUserByName(String name);
}