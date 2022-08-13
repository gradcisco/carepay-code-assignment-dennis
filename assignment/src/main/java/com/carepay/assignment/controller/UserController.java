package com.carepay.assignment.controller;

import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogUser createUser(@RequestBody BlogUser user){

        userService.createUser(user);
        return user;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BlogUser> getUsers(){

        return userService.getUsers();
    }
}
