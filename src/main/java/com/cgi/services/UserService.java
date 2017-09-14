package com.cgi.services;

import com.cgi.entities.User;
import com.cgi.utils.UserException;
import com.cgi.utils.UserNotFoundException;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User create(User u);

    List<User> findByName(String name);

    List<User> findSuggestions(Integer userId) throws UserNotFoundException;

    User findByIdNoDetail(Integer userId);

    void deleteSuggestion(Integer userId, Integer userIdSuggestion) throws UserNotFoundException;

    void patchUser(Integer userId, String patch) throws UserNotFoundException, UserException;

}