package by.restaurant.service;

import by.restaurant.model.User;

public interface UserService {

    User getUserByName(String name);

    void registerUser(User user) throws Exception;
}
