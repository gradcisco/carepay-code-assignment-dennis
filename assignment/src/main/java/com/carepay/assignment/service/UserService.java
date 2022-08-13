package com.carepay.assignment.service;

import com.carepay.assignment.dto.UserRequest;
import com.carepay.assignment.model.BlogUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserService {

    BlogUser createUser(UserRequest user);

    List<BlogUser> getUsers();
     BlogUser updateUser(UserRequest userRequest,Long id);

     BlogUser getUsersById(Long id);

     void deleteUsers();

     void deleteUserById(Long id);

}
