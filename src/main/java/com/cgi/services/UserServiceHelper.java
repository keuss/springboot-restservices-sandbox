package com.cgi.services;


import com.cgi.entities.User;
import com.cgi.repositories.UserRepository;
import com.cgi.utils.UserException;
import com.cgi.utils.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component("userServiceHelper")
public class UserServiceHelper {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceHelper.class);

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;


    public User findUser(Integer userId) {
        User u = userRepository.findOne(userId);
        if(u == null) {
            LOGGER.warn("User id {} not found", userId);
            throw new UserNotFoundException();
        }
        return u;
    }

    public CompletableFuture<JsonNode> toJsonNode(User u) {
        return CompletableFuture.supplyAsync(() -> jacksonObjectMapper.valueToTree(u));
    }

    public CompletableFuture<JsonNode> toJsonNode(String json) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return jacksonObjectMapper.readTree(json);
            } catch (IOException e) {
                throw new UserException();
            }
        });
    }

    public User toUser(JsonNode target) {
        try {
            return jacksonObjectMapper.treeToValue(target, User.class);
        } catch (JsonProcessingException e) {
            throw new UserException();
        }
    }
}
