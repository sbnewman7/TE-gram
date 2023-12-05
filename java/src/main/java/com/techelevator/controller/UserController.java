package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User updatedUser, @PathVariable int id){
        User userToUpdate = userDao.getUserById(id);
        if (userToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User NOT found");
        }
        userToUpdate.setUsername(updatedUser.getUsername());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setPicUrl(updatedUser.getPicUrl());
        return this.userDao.updateUser(userToUpdate);

    }
}
