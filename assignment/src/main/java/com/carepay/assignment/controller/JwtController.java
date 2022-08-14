package com.carepay.assignment.controller;
import com.carepay.assignment.dto.UserRequest;
import com.carepay.assignment.jwtutils.JwtUserDetailsService;
import com.carepay.assignment.jwtutils.TokenManager;
import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.model.JwtRequestModel;
import com.carepay.assignment.model.JwtResponseModel;
import com.carepay.assignment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class JwtController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserServiceImpl userService;
    @PostMapping()
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel
                                                request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }

    @PutMapping
    public ResponseEntity<BlogUser> createUser(@RequestBody UserRequest userRequest){

        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }
}
