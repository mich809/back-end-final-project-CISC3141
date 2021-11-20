package com.server.bugtracker.Bug;

import com.server.bugtracker.User.UserRepo;
import com.server.bugtracker.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope("session")
@RestController
public class BugController
{

    @Autowired
    BugService bugService;
    @Autowired
    BugRepo bugRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;

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
     * Updates a bug
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

}
