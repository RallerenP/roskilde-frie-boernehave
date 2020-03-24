package com.roskildefrieboernehave.webapp.controllers.pagecontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
