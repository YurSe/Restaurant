package by.restaurant.service;

import by.restaurant.model.User;

public interface UserService {

    User findByName(String name);

    User findByEmail(String email);

    void registerUser(User user);
}
