package com.roskildefrieboernehave.webapp.models;

import com.roskildefrieboernehave.webapp.entities.ChildEntity;
import com.roskildefrieboernehave.webapp.entities.ParentEntity;
import com.roskildefrieboernehave.webapp.services.ParentService;

import java.util.ArrayList;

public class Child {

    private static ParentService ps = ParentService.getInstance();

    private ArrayList<ParentEntity> parents = new ArrayList<>();
    private String name, birthday;
    private int ID;
    private ChildEntity ce;

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ParentEntity> getParents() {
        return parents;
    }

    public String getBirthday() {
        return birthday;
    }

    public ChildEntity _getCe() {
        return ce;
    }

    private Child(ChildEntity ce) {
        this.name = ce.getName();
        this.birthday = ce.getBirthday();
        this.ID = ce.getID();
        this.ce = ce;
    }

    public static Child fromEntity(ChildEntity ce) {
        Child child = new Child(ce);
        for (int ID: ce._getParentIds()) {
            ParentEntity pe = ps.getEntity(ID);
            child.parents.add(pe);
        }

        return child;
    }
}
