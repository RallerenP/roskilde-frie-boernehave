package com.roskildefrieboernehave.webapp.controllers;
import com.roskildefrieboernehave.webapp.models.ParentEntity;
import com.roskildefrieboernehave.webapp.services.ParentService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParentController {

    ParentService ps = new ParentService();

    @GetMapping("/parents")
    public ParentEntity[] getAll() {
        return ps.getAll();
    }

    @GetMapping("/parents/{id}")
    public ParentEntity get(
            @PathVariable("id") int id
    ) {
        return ps.get(id);
    }

    @PutMapping("/parents/{id}")
    public ParentEntity edit(
            @PathVariable("id") int id,
            @RequestBody String s
            ) {
        JSONObject o = new JSONObject(s);
        return ps.edit(id, o);
    }

    @PostMapping("/parents")
    public ParentEntity create(
            @RequestBody String s
    ) {
        JSONObject o = new JSONObject(s);
        return ps.create(o);
    }

    @DeleteMapping("/parents/{id}")
    public boolean delete(
            @PathVariable("id") int id
    ) {
        return ps.delete(id);
    }
}
