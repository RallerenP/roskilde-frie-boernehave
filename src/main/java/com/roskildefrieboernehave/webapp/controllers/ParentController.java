package com.roskildefrieboernehave.webapp.controllers;
import com.roskildefrieboernehave.webapp.models.ParentEntity;
import com.roskildefrieboernehave.webapp.services.ParentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParentController {

    ParentService ps = new ParentService();

    @GetMapping("/parents")
    public ParentEntity[] getAll() {
        return ps.getAll();
    }

}
