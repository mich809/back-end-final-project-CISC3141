package com.server.bugtracker.aws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AWSController
{
    /**
     * AWS Health check endpoint
     * @return Return health check status
     */
    @RequestMapping(method= RequestMethod.GET, value="/850F9EF8D53CF476852C69F1BB5018AF1FE3B8493A8D2AEA7B1C9398B469FF53")
    public String healthCheck()
    {
        return "ok";
    }
}
