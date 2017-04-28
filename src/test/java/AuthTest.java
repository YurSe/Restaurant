import by.restaurant.model.User;
import by.restaurant.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Pavel on 27.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:*/applicationContext.xml")
@Transactional
public class AuthTest {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    private User user;

    private String getRandomToken() {
        return RandomStringUtils.randomAlphanumeric(6).toLowerCase();
    }

    @Before
    public void initializeUser() {
        String randomToken = getRandomToken();
        user = new User();
        user.setName("Iron Man");
        user.setEmail(randomToken + "@mail.ru");
        user.setPassword("123321");
        user.setBirthday(new Date());
        user.setSubscription(true);
        user.setPhoneNumber(randomToken);
        user.setAddress("ул. Врублевского, д.86/1-26");
    }

    @Test
    public void authUserSuccessfully() throws Exception {
        User existingUser = user;
        userService.registerUser(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(existingUser.getEmail());
        Assert.assertNotNull(userDetails);
        Assert.assertEquals(user.getPassword(),
                userDetails.getPassword());
    }

    @Test
    public void authUserWithWrongPassword() throws Exception {
        String wrongPassword = "wrong password";
        User existingUser = user;
        userService.registerUser(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(existingUser.getEmail());
        Assert.assertNotNull(userDetails);
        Assert.assertNotEquals(user.getPassword(), wrongPassword);
    }

    @Test
    public void authUserWithWrongEmail() throws Exception {
        String wrongEmail = "wrong password";
        String exceptionEmail = null;
        User existingUser = user;
        userService.registerUser(user);
        try {
            userDetailsService.loadUserByUsername(wrongEmail);
        } catch (UsernameNotFoundException e) {
            exceptionEmail = e.getMessage();
        }
        Assert.assertNotNull(exceptionEmail);
    }

    @After
    public void clearUser() {
        user = null;
    }
}
