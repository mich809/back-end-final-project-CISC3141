package com.server.bugtracker.User;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Scope("session")
@Repository
@Transactional(readOnly = false)    // If necessary, can delete database entries
public interface UserRepo extends CrudRepository<User, Long>, JpaRepository<User, Long>
{

    /**
     * Gets all of the users from the User table
     * @return JSON array of users
     */
    @Query(value = "SELECT * FROM user", nativeQuery = true)
    public List<User> getUsers();
    
    
    public User findByusername(String username);

}

