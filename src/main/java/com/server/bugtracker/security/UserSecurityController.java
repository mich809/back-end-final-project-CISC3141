package com.server.bugtracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.server.bugtracker.jwt.JwtRequest;
import com.server.bugtracker.jwt.JwtResponse;
import com.server.bugtracker.user.User;



@Scope("session")
@RestController
public class UserSecurityController
{

	@Autowired
    UserSecurityService userSecurityService;

    @PostMapping({"/register"})
    public ResponseEntity<String> registerNewUser(@RequestBody User user) {
        return userSecurityService.registerNewUser(user);
    }
    
    @PostMapping({"/authenticate"})
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        return  userSecurityService.createJwtToken(jwtRequest);
    }

}
