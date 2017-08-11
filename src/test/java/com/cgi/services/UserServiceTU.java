package com.cgi.services;

import com.cgi.entities.User;
import com.cgi.entities.UserDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cgi.Application.class)
@TestPropertySource(locations="classpath:test.properties")
public class UserServiceTU {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTU.class);

    @Autowired
    UserService userService;

    @Test
    public void createUserTest() throws Exception {

        // create
        User user = new User();
        user.setName("steve in memory");
        user.setEmail("steve-mem@gmail.com");
        user.setUserDetail(new UserDetail(1, 2, 3));
        userService.create(user);

        Optional<User> mayBeUser = userService.findAll().stream().filter(u -> u.equals(user)).findFirst();
        LOGGER.info("mayBeUser : {}", mayBeUser);
        Assert.assertTrue(mayBeUser.isPresent());
    }

    @Test
    public void findByNameTest() throws Exception {
        LOGGER.info("##### {}", userService.findByName("gui").get(0).getUserDetail());
        Assert.assertTrue(!userService.findByName("gui").isEmpty());
    }
}
