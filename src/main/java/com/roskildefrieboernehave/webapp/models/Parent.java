package com.roskildefrieboernehave.webapp.models;

import com.roskildefrieboernehave.webapp.entities.ParentEntity;

import java.util.ArrayList;

public class Parent {
    ArrayList<Child> children = new ArrayList<>();
    String name, phone;
    int ID;

    public Parent(ParentEntity pe) {
        // TODO Add child service
        this.name = pe.getName();
        this.phone = pe.getPhone();
        this.ID = pe.getID();
    }

}
