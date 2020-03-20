package com.roskildefrieboernehave.webapp.services;
import com.roskildefrieboernehave.webapp.helpers.JSONHelper;
import com.roskildefrieboernehave.webapp.entities.ParentEntity;
import com.roskildefrieboernehave.webapp.utils.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class ParentService implements IService<ParentEntity> {

    private HashSet<ParentEntity> parents = new HashSet<ParentEntity>();
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
        return parentArr;
    }

    @Override
    public ParentEntity get(int ID) {
        JSONObject o = fm.extractFromParentFile(ID);
        ParentEntity parent = mapToParent(o);

        return getFromHashSet(parent);
    }

    @Override
    public ParentEntity edit(int ID, JSONObject update) {
        JSONObject old = fm.extractFromParentFile(ID);
        if (update.has("name")) old.put("name", update.getString("name"));
        if (update.has("phone")) old.put("phone", update.getString("phone"));

        parents.remove(get(ID));

        fm.writeToParentFile(ID, old);

        return getFromHashSet(mapToParent(old));
    }

    @Override
    public ParentEntity create(JSONObject json) {
        // TODO Error handling
        if (!json.has("name") || !json.has("phone")) return null;

        String name = json.getString("name"), phone = json.getString("phone");
        int ID = fm.getParentIndex() + 1;

        ParentEntity pe = new ParentEntity(name, phone, ID, new int[0]);

        fm.writeToParentFile(ID, mapToJSON(pe));
        fm.writeToParentFile("index", ID);

        return pe;
    }

    @Override
    public boolean delete(int ID) {
        parents.remove(get(ID));
        return fm.deleteFromParentFile(ID);
    }

    private ParentEntity getFromHashSet(ParentEntity parent) {
        if (parents.contains(parent)) {
            for (ParentEntity p : parents) {
                if (p.equals(parent)) return p;
            }
        }

        return parent;
    }

    private ParentEntity mapToParent(JSONObject object) {
        //TODO add error handling

        String name = object.getString("name");
        String phone = object.getString("phone");
        JSONArray childArr = object.getJSONArray("childrenIds");
        int[] childrenIds = JSONHelper.toIntArray(childArr);
        int ID = object.getInt("ID");

        return new ParentEntity(name, phone, ID, childrenIds);
    }

    private JSONObject mapToJSON(ParentEntity parent) {
        JSONObject json = new JSONObject();
        json.put("name", parent.getName());
        json.put("phone", parent.getPhone());
        json.put("ID", parent.getID());
        json.put("childrenIds", parent.getChildrenIds());
        return json;
    }
}
