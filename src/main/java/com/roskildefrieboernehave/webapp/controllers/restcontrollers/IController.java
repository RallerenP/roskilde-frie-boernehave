package com.roskildefrieboernehave.webapp.controllers.restcontrollers;

public interface IController<T> {
    public T[] getAll();
    public T get(int ID);
    public T edit(int ID, String JSON);
    public T create(String JSON);
    public boolean delete(int ID);
}
