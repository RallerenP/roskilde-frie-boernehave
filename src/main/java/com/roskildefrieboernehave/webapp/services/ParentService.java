package com.roskildefrieboernehave.webapp.services;
import com.roskildefrieboernehave.webapp.helpers.JSONHelper;
import com.roskildefrieboernehave.webapp.models.ParentEntity;
import com.roskildefrieboernehave.webapp.utils.FileManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;


public class ParentService implements IService<ParentEntity> {

    private HashSet<ParentEntity> parents = new HashSet<>();
    private FileManager fm = FileManager.getInstance();

    @Override
    public ParentEntity[] getAll() {
        JSONObject o = fm.extractFromParentFile();
        ArrayList<JSONObject> arr = JSONHelper.getKeyArray(o);
        for (JSONObject x : arr) {
            parents.add(mapToParent(x));
        }
        ParentEntity[] parentArr = new ParentEntity[parents.size()];
        parents.toArray(parentArr);
        System.out.println(parentArr[0].equals(parentArr[2]));
        return parentArr;

    }

    private ParentEntity mapToParent(JSONObject object) {
        //TODO add error handling
        String name = object.getString("name");
        String phone = object.getString("phone");
        return new ParentEntity(name, phone);
    }
}
