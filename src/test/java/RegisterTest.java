import by.restaurant.model.User;
import by.restaurant.repository.UserRepository;
import by.restaurant.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Pavel on 25.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:*/applicationContext.xml")
@Transactional
public class RegisterTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    private String getRandomToken() {
        return RandomStringUtils.randomAlphanumeric(6).toLowerCase();
    }

    @Before
    public void initializeUser() {
        user = new User();
        user.setName("Iron Man");
        user.setEmail(getRandomToken() + "@mail.ru");
        user.setPassword("123321");
        user.setBirthday(new Date());
        user.setSubscription(true);
        user.setPhoneNumber(getRandomToken());
        user.setAddress("ул. Врублевского, д.86/1-26");
    }

    @Test
    public void registerUser() throws Exception {
        User existingUser = user;
        userService.registerUser(user);
        Assert.assertEquals(user.getEmail(),
                userRepository.findByEmail(user.getEmail()).getEmail());
        Assert.assertEquals(user.getPhoneNumber(),
                userRepository.findByPhoneNumber(existingUser.getPhoneNumber()).getPhoneNumber());
    }

    @After
    public void clearUser() {
        user = null;
    }

}
