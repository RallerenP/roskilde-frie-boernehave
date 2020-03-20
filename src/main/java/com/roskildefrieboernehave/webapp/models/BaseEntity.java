package com.roskildefrieboernehave.webapp.models;

public abstract class BaseEntity {
    private int ID;

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
