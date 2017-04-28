package by.restaurant.service.implementation;

import by.restaurant.model.User;
import by.restaurant.repository.UserRepository;
import by.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Pavel on 15.04.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) throws Exception {
        userRepository.save(user);
    }
}
