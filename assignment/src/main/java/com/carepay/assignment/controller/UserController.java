package com.carepay.assignment.controller;

import com.carepay.assignment.dto.UserRequest;
import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BlogUser> createUser(@RequestBody UserRequest userRequest){


        return new ResponseEntity<>(userService.createUser(userRequest),HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BlogUser> getUsers(){

        return userService.getUsers();
    }
}
