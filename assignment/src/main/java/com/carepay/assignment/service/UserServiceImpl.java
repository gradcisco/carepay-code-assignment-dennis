package com.carepay.assignment.service;

import com.carepay.assignment.dto.UserRequest;
import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public BlogUser createUser(UserRequest userRequest) {
        BlogUser user = new BlogUser();

        user.setUserName(userRequest.getUserName());
        user.setUserPassword(new BCryptPasswordEncoder().encode(userRequest.getUserPassword()));

        user = userRepository.save(user);
        System.out.println(user);
        return user;
    }

    @Override
    public List<BlogUser> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public BlogUser updateUser(UserRequest userRequest,Long id,String principal) throws Exception {

        BlogUser user = userRepository.findById(id).orElseThrow();

        //if blog belongs to another user
        if(!user.getUserName().equalsIgnoreCase(principal))
            throw new Exception("Cannot edit details for another user");

        user.setUserPassword(new BCryptPasswordEncoder().encode(userRequest.getUserPassword()));
        user = userRepository.save(user);

        return user;
    }

    @Override
    public BlogUser getUsersById(Long id) {

        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteUsers() {

        userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id,String principal) throws Exception {

        BlogUser user = userRepository.findById(id).orElseThrow();

        if(!user.getUserName().equalsIgnoreCase(principal))
            throw new Exception("Cannot delete user not self");
        userRepository.deleteById(id);
    }
}
