package com.cgi.services;

import com.cgi.entities.User;
import com.cgi.entities.UserDetail;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface UserService {

    List<User> findAll();

    User create(User u);

    List<User> findByName(String name);

    List<User> findSuggestions(Integer userId);

    User findByIdNoDetail(Integer userId);

    void deleteSuggestion(Integer userId, Integer userIdSuggestion);

    CompletableFuture<User> patchUser(Integer userId, String patch);

    UserDetail getUserDetail(Integer userId);

}