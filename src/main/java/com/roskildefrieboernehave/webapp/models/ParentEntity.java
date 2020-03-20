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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ParentEntity)) {
            return false;
        }
        ParentEntity otherP = (ParentEntity) other;
        return (otherP.name.equals(this.name)) && otherP.phone.equals(this.phone);
    }

    @Override
    public int hashCode() {
        int res = 0;
        res = res + name.hashCode();
        res = res + phone.hashCode();
        return res;
    }

}
