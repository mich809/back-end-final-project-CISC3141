package com.server.bugtracker.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("session")
@Service
public class UserService {

    final UserRepo userRepo;

    /**
     * Constructor injection
     * @param userRepo
     */
    public UserService(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    /**
     * Gets all users in User table
     * @return JSON array of users
     */
    public List<User> getUsers()
    {
        return userRepo.getUsers();
    }

}
