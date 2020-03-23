package com.roskildefrieboernehave.webapp.controllers;

import com.roskildefrieboernehave.webapp.entities.ChildEntity;
import com.roskildefrieboernehave.webapp.entities.ParentEntity;
import com.roskildefrieboernehave.webapp.services.ChildService;
import com.roskildefrieboernehave.webapp.services.ParentService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChildController {
    ChildService cs = new ChildService();

    @GetMapping("/children")
    public ChildEntity[] getAll() {
        return cs.getAll();
    }

    @GetMapping("/children/{id}")
    public ChildEntity get(
            @PathVariable("id") int id
    ) {
        return cs.get(id);
    }

    @PutMapping("/children/{id}")
    public ChildEntity edit(
            @PathVariable("id") int id,
            @RequestBody String s
    ) {
        JSONObject o = new JSONObject(s);
        return cs.edit(id, o);
    }

    @PostMapping("/children")
    public ChildEntity create(
            @RequestBody String s
    ) {
        JSONObject o = new JSONObject(s);
        return cs.create(o);
    }

    @DeleteMapping("/children/{id}")
    public boolean delete(
            @PathVariable("id") int id
    ) {
        return cs.delete(id);
    }
}
