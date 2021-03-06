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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cgi.Application.class)
public class UserServiceTI {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTI.class);

    @Autowired
    UserService userService;

    @Test
    public void createUserTest() throws Exception {

        // create
        User user = new User();
        user.setName("steve");
        user.setEmail("steve@gmail.com");
        user.setUserDetail(new UserDetail(0L, 2L, 3L, Arrays.asList(1, 2)));
        userService.create(user);

        // userService.findAll().stream().forEach(System.out::println);
        Optional<User> mayBeUser = userService.findAll().stream().filter(u -> u.equals(user)).findFirst();
        LOGGER.info("mayBeUser : {}", mayBeUser);
        Assert.assertTrue(mayBeUser.isPresent());
    }


    @Test
    public void findByNameTest() throws Exception {
        // find
        userService.findByName("gui").stream().forEach(System.out::println);
        Assert.assertTrue(!userService.findByName("gui").isEmpty());
    }
}
