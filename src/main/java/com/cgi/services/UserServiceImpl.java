package com.cgi.services;

import com.cgi.entities.User;
import com.cgi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    protected UserRepository userRepository;

    @Override
    public List<User> findAll() {
        LOGGER.info("Call findAll");
        return userRepository.findAll();
    }

    @Override
    public User create(User u) {
        LOGGER.info("Call create with {}", u);
        return userRepository.save(u);
    }

    @Override
    public List<User> findByName(String name) {
        LOGGER.info("Call findByName with {}", name);
        return userRepository.findByName(name);
    }
}
