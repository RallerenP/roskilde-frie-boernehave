package com.roskildefrieboernehave.webapp.controllers;

import com.roskildefrieboernehave.webapp.entities.ChildEntity;
import com.roskildefrieboernehave.webapp.models.Child;
import com.roskildefrieboernehave.webapp.services.ChildService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChildController {
    ChildService cs = ChildService.getInstance();

    @GetMapping("/children")
    public Child[] getAll() {
        return cs.getAll();
    }

    @GetMapping("/children/{id}")
    public Child get(
            @PathVariable("id") int id
    ) {
        return cs.get(id);
    }

    @PutMapping("/children/{id}")
    public Child edit(
            @PathVariable("id") int id,
            @RequestBody String s
    ) {
        JSONObject o = new JSONObject(s);
        return cs.edit(id, o);
    }

    @PostMapping("/children")
    public Child create(
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
