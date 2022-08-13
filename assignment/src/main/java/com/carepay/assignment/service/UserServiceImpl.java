package com.carepay.assignment.service;

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
    public BlogUser createUser(BlogUser user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public List<BlogUser> getUsers() {
        return userRepository.findAll();
    }
}
