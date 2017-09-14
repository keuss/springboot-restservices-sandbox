package com.cgi.services;

import com.cgi.entities.User;
import com.cgi.repositories.UserRepository;
import com.flipkart.zjsonpatch.JsonPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private UserServiceHelper userServiceHelper;

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

    @Override
    public List<User> findSuggestions(Integer userId) {
        return userServiceHelper.findUser(userId).getUserDetail().getSuggestions()
                .parallelStream()
                .map(id -> userRepository.findByIdNoDetail(id))
                .filter(user -> user != null)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSuggestion(Integer userId, Integer userIdSuggestion) {
        User u =  userServiceHelper.findUser(userId);
        List<Integer> suggestions = u.getUserDetail().getSuggestions().stream().filter(p -> p != userIdSuggestion).collect(Collectors.toList());
        u.getUserDetail().setSuggestions(suggestions);
        userRepository.save(u);
    }

    @Override
    public CompletableFuture<User> patchUser(Integer userId, String patch) {
        // FIXME dirty patch implementation ... (too many conversions)
        return userServiceHelper.toJsonNode(userServiceHelper.findUser(userId))
                .thenCombineAsync(userServiceHelper.toJsonNode(patch), (userNode, patchNode) -> JsonPatch.apply(patchNode, userNode))
                .thenApply(target -> userServiceHelper.toUser(target))
                .thenApply(user -> userRepository.save(user));
    }
}
