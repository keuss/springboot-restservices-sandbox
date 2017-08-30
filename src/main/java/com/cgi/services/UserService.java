package com.cgi.services;

import com.cgi.entities.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User create(User u);

    List<User> findByName(String name);

    List<User> findFollowers(Integer userId);

    User findByIdNoDetail(Integer userId);
}