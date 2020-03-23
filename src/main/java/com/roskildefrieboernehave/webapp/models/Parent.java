package com.roskildefrieboernehave.webapp.models;

import com.roskildefrieboernehave.webapp.entities.ChildEntity;
import com.roskildefrieboernehave.webapp.entities.ParentEntity;
import com.roskildefrieboernehave.webapp.services.ChildService;

import java.util.ArrayList;

public class Parent {
    private static ChildService cs = ChildService.getInstance();

    private ArrayList<ChildEntity> children = new ArrayList<>();
    private String name, phone;
    private int ID;
    private ParentEntity pe;


    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ChildEntity> getChildren() {
        return children;
    }

    public String getPhone() {
        return phone;
    }

    public ParentEntity _getPe() {
        return pe;
    }

    private Parent(ParentEntity pe) {
        this.name = pe.getName();
        this.phone = pe.getPhone();
        this.ID = pe.getID();
        this.pe = pe;
    }

    public static Parent fromEntity(ParentEntity pe) {
        Parent parent = new Parent(pe);
        for (int ID : pe._getChildrenIds()) {
            ChildEntity ce = cs.getEntity(ID);
            parent.children.add(ce);
        }
        return parent;
    }

}
