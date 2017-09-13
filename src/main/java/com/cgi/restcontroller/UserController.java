package com.cgi.restcontroller;

import com.cgi.entities.User;
import com.cgi.services.UserService;
import com.cgi.utils.BadRequestException;
import com.cgi.utils.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findSuggestions(@PathVariable("userId") Integer userId) {
        //FIXME : probably add paging stuff !
        try {
            return new ResponseEntity<>(userService.findSuggestions(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            LOGGER.warn("Unable to find suggestions for userId " + userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", headers = {"Content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult bindingResults) {
        if (bindingResults.hasErrors() || user == null || user.getName() == null || user.getName().isEmpty()) {
            throw new BadRequestException();
        } else {
            return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/suggestions/{userIdSuggestion}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSuggestion(@PathVariable("userId") Integer userId, @PathVariable("userIdSuggestion") Integer userIdSuggestion) {
        try {
            userService.deleteSuggestion(userId, userIdSuggestion);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            LOGGER.warn("Unable to delete suggestion for userId " + userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
