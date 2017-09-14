package com.cgi.restcontroller;

import com.cgi.entities.User;
import com.cgi.services.UserService;
import com.cgi.utils.BadRequestException;
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
import java.util.concurrent.Future;

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
    public List<User> findSuggestions(@PathVariable("userId") Integer userId) {
        //FIXME : probably add paging stuff !
        return userService.findSuggestions(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", headers = {"Content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult bindingResults) {
        if (bindingResults.hasErrors()) {
            throw new BadRequestException();
        } else {
            return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/suggestions/{userIdSuggestion}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSuggestion(@PathVariable("userId") Integer userId, @PathVariable("userIdSuggestion") Integer userIdSuggestion) {
        userService.deleteSuggestion(userId, userIdSuggestion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @param userId
     * @param patchPayload
     *  [
            { "op": "replace", "path": "/userDetail/followingNb", "value": 111 }
        ]

        [
            { "op": "replace", "path": "/userDetail", "value": {"postsNb": 111,"followersNb": 222,"followingNb": 333,"suggestions": [444]} }
        ]
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/users/{userId}", headers = {"Content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Future<User> updateUser(@PathVariable("userId") Integer userId, @RequestBody String patchPayload) {
        return userService.patchUser(userId, patchPayload);
    }
}
