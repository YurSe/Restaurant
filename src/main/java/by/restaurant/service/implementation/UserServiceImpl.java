package by.restaurant.service.implementation;

import by.restaurant.controller.UserContr;
import by.restaurant.model.User;
import by.restaurant.repository.UserRepository;
import by.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerUser(UserContr userContrModel) throws Exception {
        User user = new User();
        user.setBirthday(userContrModel.getBirthday());
        user.setSubscription(userContrModel.isHasSubscription());
        user.setPhoneNumber(userContrModel.getPhoneNumber());
        user.setName(userContrModel.getName());
        user.setAddress(userContrModel.getAddress());
        user.setPassword(bCryptPasswordEncoder.encode(userContrModel.getPassword()));
        user.setEmail(userContrModel.getEmail());
        userRepository.save(user);
    }
}
