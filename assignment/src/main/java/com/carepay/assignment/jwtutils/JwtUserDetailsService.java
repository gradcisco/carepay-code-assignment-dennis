package com.carepay.assignment.jwtutils;

import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BlogUser> user = userRepository.findByUserName(username);
            if ("randomuser123".equals(username)) {
            return new User("randomuser123",
                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
           // throw new UsernameNotFoundException("User not found with username: " + username);
        }
        if(user.isPresent()){
            return new User(user.get().getUserName(),
                    user.get().getUserPassword(),
                    new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }
}