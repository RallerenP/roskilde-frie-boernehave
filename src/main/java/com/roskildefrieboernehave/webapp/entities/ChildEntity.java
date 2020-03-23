package com.roskildefrieboernehave.webapp.entities;

public class ChildEntity extends BaseEntity {
    String name, birthday;
    int[] parentIds;

    public ChildEntity(String name, String birthday, int ID, int[] parentIds) {
        this.name = name;
        this.birthday = birthday;
        this.ID = ID;
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public int[] getParentIds() {
        return parentIds;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ChildEntity)) {
            return false;
        }
        ChildEntity otherP = (ChildEntity) other;
        return
                (otherP.name.equals(this.name)) && otherP.birthday.equals(this.birthday) && otherP.ID == this.ID;
    }

    @Override
    public int hashCode() {
        int res = 0;
        res = res + name.hashCode();
        res = res + birthday.hashCode();
        res = res + ID;
        return res;
    }
}
