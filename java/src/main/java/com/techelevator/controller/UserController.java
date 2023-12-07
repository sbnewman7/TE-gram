package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@CrossOrigin
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User updatedUser, @PathVariable int id, Principal principal){
        User currentUser = this.userDao.getUserByUsername(principal.getName());
    if(currentUser.getId()!= id){
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized");
    }
        User userToUpdate = userDao.getUserById(id);
        if (userToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User NOT found");
        }
        userToUpdate.setUsername(updatedUser.getUsername());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setPicUrl(updatedUser.getPicUrl());
        return this.userDao.updateUser(userToUpdate);
    }

    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public User getUser(@PathVariable String username) {
    User retrieveUser = this.userDao.getUserByUsername(username);
    if (retrieveUser == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User NOT found");
    }
        return retrieveUser;
    }

    @GetMapping(path = "/user/{id}")
    public User getUserById(@PathVariable int id) {
        User user = this.userDao.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User NOT found");
        }
        return user;
    }

}
