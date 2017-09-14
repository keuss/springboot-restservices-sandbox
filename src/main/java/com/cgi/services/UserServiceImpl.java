package com.cgi.services;

import com.cgi.entities.User;
import com.cgi.repositories.UserRepository;
import com.cgi.utils.UserException;
import com.cgi.utils.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User u) {
        return userRepository.save(u);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByIdNoDetail(Integer userId) {
        return userRepository.findByIdNoDetail(userId);
    }

    private User findUser(Integer userId) throws UserNotFoundException {
        User u = userRepository.findOne(userId);
        if(u == null) {
            LOGGER.warn("User id {} not found", userId);
            throw new UserNotFoundException();
        }
        return u;
    }

    @Override
    public List<User> findSuggestions(Integer userId) throws UserNotFoundException {
        return findUser(userId).getUserDetail().getSuggestions()
                .parallelStream()
                .map(id -> userRepository.findByIdNoDetail(id))
                .filter(user -> user != null)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSuggestion(Integer userId, Integer userIdSuggestion) throws UserNotFoundException {
        User u =  findUser(userId);
        List<Integer> suggestions = u.getUserDetail().getSuggestions().stream().filter(p -> p != userIdSuggestion).collect(Collectors.toList());
        u.getUserDetail().setSuggestions(suggestions);
        userRepository.save(u);
    }

    private CompletableFuture<JsonNode> toJsonNode(User u) {
        return CompletableFuture.supplyAsync(() -> jacksonObjectMapper.valueToTree(u));
    }

    private CompletableFuture<JsonNode> toJsonNode(String json) throws UserException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return jacksonObjectMapper.readTree(json);
            } catch (IOException e) {
                throw new UserException();
            }
        });
    }

    @Override
    public void patchUser(Integer userId, String patch) throws UserNotFoundException, UserException {
        // FIXME dirty path implementation ... (too many conversions)
        toJsonNode(findUser(userId))
                .thenCombine(toJsonNode(patch), (userNode, patchNode) -> JsonPatch.apply(patchNode, userNode))
                .thenApply(target -> {
                    try {
                        return jacksonObjectMapper.treeToValue(target, User.class);
                    } catch (JsonProcessingException e) {
                        throw new UserException();
                    }
                })
                .thenAccept(user -> userRepository.save(user));
    }
}
