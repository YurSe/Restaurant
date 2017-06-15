package by.restaurant.service.implementation;

import by.restaurant.model.User;
import by.restaurant.repository.IUserRepository;
import by.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public User getById(Long id) {
        return iUserRepository.getOne(id);
    }

    @Override
    public User save(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        iUserRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return iUserRepository.findAll();
    }
}
