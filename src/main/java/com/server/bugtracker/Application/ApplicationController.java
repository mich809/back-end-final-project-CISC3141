package com.server.bugtracker.Application;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController
{

    /**
     * Serves homepage
     * @return
     */
    @RequestMapping("/")
    public String home()
    {

        return "test";
    }


}
