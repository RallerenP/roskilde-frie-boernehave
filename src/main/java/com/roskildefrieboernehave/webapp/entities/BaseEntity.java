package com.roskildefrieboernehave.webapp.entities;

public abstract class BaseEntity {
    int ID;

    public int getID() {
        return ID;
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
