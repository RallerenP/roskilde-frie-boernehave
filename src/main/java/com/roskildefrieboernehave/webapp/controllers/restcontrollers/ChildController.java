package com.roskildefrieboernehave.webapp.controllers.restcontrollers;

import com.roskildefrieboernehave.webapp.models.Child;
import com.roskildefrieboernehave.webapp.services.ChildService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChildController implements IController{
    ChildService cs = ChildService.getInstance();

    @Override
    @GetMapping("/children")
    public Child[] getAll() {
        return cs.getAll();
    }

    @Override
    @GetMapping("/children/{id}")
    public Child get(
            @PathVariable("id") int id
    ) {
        return cs.get(id);
    }

    @Override
    @PutMapping("/children/{id}")
    public Child edit(
            @PathVariable("id") int id,
            @RequestBody String s
    ) {
        JSONObject o = new JSONObject(s);
        return cs.edit(id, o);
    }

    @Override
    @PostMapping("/children")
    public Child create(
            @RequestBody String s
    ) {
        JSONObject o = new JSONObject(s);
        return cs.create(o);
    }

    @Override
    @DeleteMapping("/children/{id}")
    public boolean delete(
            @PathVariable("id") int id
    ) {
        return cs.delete(id);
    }
}
