package com.server.bugtracker.bug;

import com.server.bugtracker.user.UserRepo;
import com.server.bugtracker.user.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope("session")
@RestController
public class BugController
{

    final BugService bugService;
    final BugRepo bugRepo;
    final UserRepo userRepo;
    final UserService userService;

    /**
     * Constructor injection
     * @param bugService
     * @param bugRepo
     * @param userRepo
     * @param userService
     */
    public BugController(BugService bugService, BugRepo bugRepo, UserRepo userRepo, UserService userService)
    {
        this.bugService = bugService;
        this.bugRepo = bugRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    /**
     * Gets all bugs in database
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get-bugs")
    public List<Bug> getBugs()
    {
        return bugService.getBugs();
    }

    /**
     * Creates a bug request
     * POST request must be JSON format
     * @param bug
     */
    @RequestMapping(method = RequestMethod.POST, value = "/create-bug")
    public void createBug(@RequestBody Bug bug)
    {
        bugService.createBug(bug);
    }

    /**
     * Notes a bug
     * @param bug
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update-bug")
    public void updateBug(@RequestBody Bug bug)
    {
        // Backend send a list of all the bugs to the frontend -> as JSON
        // Frontend is going to parse it and populate the dashboard/table
        // When a user clicks "Edit" button, frontend is going to send data (JSON)
        // Send it as a Bug/JSON; current id saved in the JSON to be sent
        // Frontend will have to send who the bug is assigned to and created by
        // Due date comes from frontend

        bugService.updateBug(bug);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/get-bug")
    public Bug getBug(@RequestBody String id)
    {
        return  bugService.getBug( id );
    }


}
