package com.cgi.restcontroller;

import com.cgi.entities.User;
import com.cgi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
        LOGGER.info("Call for findAll ...");
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findSuggestions(@PathVariable("userId") Integer userId) {
        LOGGER.info("Call for findSuggestions ...");
        //FIXME : probably add paging stuff !
        return userService.findSuggestions(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", headers = {"Content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@Valid @RequestBody User user, BindingResult bindingResults) throws Exception {
        if (bindingResults.hasErrors()) {
            throw new Exception("Input invalid");
        } else {
            return this.userService.create(user);
        }
    }
}
