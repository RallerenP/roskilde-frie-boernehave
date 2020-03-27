package com.roskildefrieboernehave.webapp.controllers.pagecontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/indmeld")
    public String getHome() {
        return "indmeld";
    }

    @GetMapping("/success")
    public String getSuccess() {
        return "success";
    }

    @GetMapping("/error")
    public String getError() {
        return "error";
    }

    @GetMapping("/view")
    public String getView() {
        return "overview";
    }
}
