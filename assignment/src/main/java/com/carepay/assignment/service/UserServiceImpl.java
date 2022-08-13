package com.carepay.assignment.service;

import com.carepay.assignment.dto.UserRequest;
import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public BlogUser createUser(UserRequest userRequest) {
        BlogUser user = new BlogUser();

        user.setUserName(userRequest.getUserName());
        user.setUserPassword(userRequest.getUserPassword());

        user = userRepository.save(user);
        System.out.println(user);
        return user;
    }

    @Override
    public List<BlogUser> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public BlogUser updateUser(UserRequest userRequest,Long id) {
        BlogUser user = new BlogUser();
        user.setId(id);

        user.setUserName(userRequest.getUserName());
        user.setUserPassword(userRequest.getUserPassword());

        user.setLastLoginDate(Date.from(Instant.now()));
        user = userRepository.save(user);

        System.out.println(user);

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
    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }
}
