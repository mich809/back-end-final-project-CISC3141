package com.server.bugtracker.bug;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("session")
@Service
public class BugService
{

    final BugRepo bugRepo;

    /**
     * Constructor injection
     * @param bugRepo
     */
    public BugService(BugRepo bugRepo)
    {
        this.bugRepo = bugRepo;
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
     * Adds bug to Bug table
     * @param bug
     */
    public void createBug(Bug bug)
    {
        bugRepo.save(bug);
    }

    /**
     * Updates a bug
     * @param bug
     */
    public void updateBug(Bug bug)
    {
        // There needs to be more logic here to check if an update is allowed to happen
        // Waiting for frontend team to provide more input on what needs to be included
        bugRepo.updateBug( bug.getId(), bug.getTitle(), bug.getBug_description(),
                bug.getDue_date(), bug.getAssigned_to(), bug.getCreated_by(),
                bug.getSeverity(), bug.getBug_status() );
    }

    // Get a single bug
    public Bug getBug( String id )
    {
        return bugRepo.getBug( Long.parseLong( id ) );
    }

}
