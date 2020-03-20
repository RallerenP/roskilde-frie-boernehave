package com.roskildefrieboernehave.webapp.services;
import com.roskildefrieboernehave.webapp.models.ParentEntity;
import java.util.HashSet;


public class ParentService implements IService<ParentEntity> {

    private HashSet<ParentEntity> parents = new HashSet<>();

    @Override
    public ParentEntity[] getAll() {
        parents.add(new ParentEntity("Jørgen", "12345678"));
        parents.add(new ParentEntity("Jesper", "99999999"));
        ParentEntity[] parentArr = new ParentEntity[parents.size()];
        parents.toArray(parentArr);
        return parentArr;

    }
}
