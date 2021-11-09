package com.server.bugtracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope("session")
@RestController
public class UserController
{

    @Autowired
    UserService userService;

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
