package com.server.bugtracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("session")
@Service
public class UserService
{

    @Autowired
    UserRepo userRepo;

    /**
     * Gets all users in User table
     * @return JSON array of users
     */
    public List<User> getUsers()
    {
        return userRepo.getUsers();
    }
}
