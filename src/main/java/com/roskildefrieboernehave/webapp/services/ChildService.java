package com.roskildefrieboernehave.webapp.services;

import com.roskildefrieboernehave.webapp.entities.ChildEntity;
import org.json.JSONObject;

public class ChildService implements IService<ChildEntity> {
    @Override
    public ChildEntity[] getAll() {
        return new ChildEntity[0];
    }

    @Override
    public ChildEntity get(int ID) {
        return null;
    }

    @Override
    public ChildEntity edit(int ID, JSONObject o) {
        return null;
    }

    @Override
    public ChildEntity create(JSONObject o) {
        return null;
    }

    @Override
    public boolean delete(int ID) {
        return false;
    }
}
