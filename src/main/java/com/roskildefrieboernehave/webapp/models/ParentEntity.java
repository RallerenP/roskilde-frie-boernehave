package com.roskildefrieboernehave.webapp.models;

public class ParentEntity extends BaseEntity {

    private String name, phone;
    private int ID;

    public ParentEntity(String name, String phone, int ID) {
        this.name = name;
        this.phone = phone;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ParentEntity)) {
            return false;
        }
        ParentEntity otherP = (ParentEntity) other;
        return
                (otherP.name.equals(this.name)) && otherP.phone.equals(this.phone) && otherP.getID() == this.ID;
    }

    @Override
    public int hashCode() {
        int res = 0;
        res = res + name.hashCode();
        res = res + phone.hashCode();
        res = res + ID;
        return res;
    }

}
