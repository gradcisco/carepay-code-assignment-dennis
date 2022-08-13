package com.carepay.assignment.service;

import com.carepay.assignment.model.BlogUser;

import java.util.List;

public interface UserService {

    BlogUser createUser(BlogUser user);

    List<BlogUser> getUsers();
}
