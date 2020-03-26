package com.roskildefrieboernehave.webapp.controllers.restcontrollers;

import com.roskildefrieboernehave.webapp.helpers.ReturnEntity;

public interface IController<T> {
    public ReturnEntity<T[]> getAll();
    public ReturnEntity<T> get(int ID);
    public ReturnEntity<T> edit(int ID, String JSON);
    public ReturnEntity<T> create(String JSON);
    public ReturnEntity<Boolean> delete(int ID);
}
