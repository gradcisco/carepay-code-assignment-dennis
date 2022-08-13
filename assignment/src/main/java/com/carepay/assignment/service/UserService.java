package com.carepay.assignment.service;

import com.carepay.assignment.dto.UserRequest;
import com.carepay.assignment.model.BlogUser;

import java.util.List;

public interface UserService {

    BlogUser createUser(UserRequest user);

    List<BlogUser> getUsers();
}
