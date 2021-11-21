package com.server.bugtracker.User;

import com.server.bugtracker.Bug.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("session")
@Service
public class UserService {

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

    /**
     * Gets the id of a user
     * @return Long value
     */
    public Long getUserId()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return userRepo.getId( name );
    }


}
