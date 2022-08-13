package com.carepay.assignment.service;

import com.carepay.assignment.dto.UserRequest;
import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public BlogUser createUser(UserRequest userRequest) {
        BlogUser user = BlogUser
                .build(0L,userRequest.getUserName(), userRequest.getUserPassword());

        user = userRepository.save(user);
        System.out.println(user);
        return user;
    }

    @Override
    public List<BlogUser> getUsers() {
        return userRepository.findAll();
    }
}
