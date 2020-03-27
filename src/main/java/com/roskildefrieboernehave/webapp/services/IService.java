package com.roskildefrieboernehave.webapp.services;

import com.roskildefrieboernehave.webapp.entities.BaseEntity;
import com.roskildefrieboernehave.webapp.helpers.ReturnEntity;
import org.json.JSONObject;

public interface IService<T> {

    ReturnEntity<T[]> getAll();
    ReturnEntity<T> get(int ID);
    ReturnEntity<T> edit(int ID, JSONObject o);
    ReturnEntity<T> create(JSONObject o);
    ReturnEntity<Boolean> delete(int ID);
}
