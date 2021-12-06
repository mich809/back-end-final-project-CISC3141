package com.server.bugtracker.bug;

import com.server.bugtracker.user.UserRepo;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("session")
@Service
public class BugService
{

    final BugRepo bugRepo;
    final UserRepo userRepo;

    /**
     * Constructor injection
     * @param bugRepo
     * @param userRepo
     */
    public BugService(BugRepo bugRepo, UserRepo userRepo)
    {
        this.bugRepo = bugRepo;
        this.userRepo = userRepo;
    }

    /**
     * Gets all bugs in Bug table
     * @return JSON array of bugs
     */
    public List<Bug> getBugs()
    {
        return bugRepo.getBugs();
    }

    /**
     * Adds a bug to the Bug table
     * @param bug
     * @return 201 status code (Created) - if successful
     * @return 422 status code (Unprocessable Entity) - if missing required data fields
     */
    public ResponseEntity<String> createBug(Bug bug)
    {
        if( bug.validBug() )
        {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            long id = userRepo.getId( auth.getName() );
            bug.setCreated_by( id );
            bugRepo.save( bug );
            return new ResponseEntity<String>("Bug successfully created", HttpStatus.CREATED);
        } else
        {
            return new ResponseEntity<String>("Missing required fields", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Updates a bug
     * @param bug
     */
    public void updateBug(Bug bug)
    {
        // TODO There needs to be more logic here to check if an update is allowed to happen
        //  - Waiting for frontend team to provide more input on what needs to be included
        bugRepo.updateBug( bug.getId(), bug.getTitle(), bug.getBug_description(),
                bug.getDue_date(), bug.getAssigned_to(), bug.getCreated_by(),
                bug.getSeverity(), bug.getBug_status() );
    }

    /**
     * Gets a single bug from the database
     * @param id
     * @return Bug entry
     */
    public Bug getBug( long id )
    {
        return bugRepo.getBug( id );
    }

    /**
     * Delete bug from database
     * @param bug
     */
    public void deleteBug( Bug bug )
    {
        bugRepo.deleteBug( bug.getId() );
    }

}
