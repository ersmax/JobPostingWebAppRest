package com.example.JobAppRest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Without this, refreshing the browser on /create or /edit gives a 404,
 * because Spring doesn't know those React routes.
 */
@Controller
public class SpaController {

    @GetMapping({"/create", "/edit"})
    public String forward() {
        return "forward:/index.html";
    }
}