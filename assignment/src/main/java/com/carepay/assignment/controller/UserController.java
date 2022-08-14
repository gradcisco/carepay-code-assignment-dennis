package com.carepay.assignment.controller;

import com.carepay.assignment.dto.UserRequest;
import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.service.UserService;
import com.carepay.assignment.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "This method is used to update a blog users with the given identifier")
    public ResponseEntity<BlogUser> updateUser(@RequestBody UserRequest userRequest,@PathVariable Long id){


        return new ResponseEntity<>(userService.updateUser(userRequest,id),HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BlogUser> getUsers(){

        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogUser> getUsersById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUsersById(id),HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteUsers(){
        userService.deleteUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogUser> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
