package com.server.bugtracker.user;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope("session")
@RestController
public class UserController
{

    final UserService userService;

    /**
     * Constructor injection
     * @param userService
     */
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Gets all users in User table
     * @return JSON array of users
     */
    @RequestMapping(method = RequestMethod.GET, value ="/get-users")
    public List<User> getUsers()
    {
        return userService.getUsers();
    }



}
