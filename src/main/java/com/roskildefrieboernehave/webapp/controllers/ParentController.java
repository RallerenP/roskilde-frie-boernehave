package com.roskildefrieboernehave.webapp.controllers;
import com.roskildefrieboernehave.webapp.models.Parent;
import com.roskildefrieboernehave.webapp.services.ParentService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParentController {

    ParentService ps = ParentService.getInstance();

    @GetMapping("/parents")
    public Parent[] getAll() {
        return ps.getAll();
    }

    @GetMapping("/parents/{id}")
    public Parent get(
            @PathVariable("id") int id
    ) {
        return ps.get(id);
    }

    @PutMapping("/parents/{id}")
    public Parent edit(
            @PathVariable("id") int id,
            @RequestBody String s
            ) {
        JSONObject o = new JSONObject(s);
        return ps.edit(id, o);
    }

    @PostMapping("/parents")
    public Parent create(
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
