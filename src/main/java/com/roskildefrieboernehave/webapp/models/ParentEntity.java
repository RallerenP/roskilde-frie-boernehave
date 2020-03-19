package com.roskildefrieboernehave.webapp.models;

public class ParentEntity {

    private String name, phone;

    public ParentEntity(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
