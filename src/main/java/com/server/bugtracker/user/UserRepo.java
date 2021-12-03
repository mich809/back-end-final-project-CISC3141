package com.server.bugtracker.user;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    /**
     * Finds user
     * Used for security
     * @param username
     * @return
     */
    public User findByusername(String username);

    /**
     * Gets the id of a user
     * @return Long value
     */
    @Query(value = "SELECT u.id FROM user AS u WHERE u.user_name = ?1", nativeQuery = true)
    public Long getId( @Param( "username" ) String username );
    
    boolean existsByusername(String username);





}

