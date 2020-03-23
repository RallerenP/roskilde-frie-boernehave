package com.roskildefrieboernehave.webapp.services;

import com.roskildefrieboernehave.webapp.entities.ChildEntity;
import com.roskildefrieboernehave.webapp.helpers.JSONHelper;
import com.roskildefrieboernehave.webapp.models.Child;
import com.roskildefrieboernehave.webapp.utils.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class ChildService implements IService<ChildEntity> {
    private HashSet<ChildEntity> children = new HashSet<>();
    private FileManager fm = FileManager.getInstance();

    private static ChildService instance = new ChildService();
    public static ChildService getInstance() { return instance; }
    private ChildService() {}


    public Child[] getAll() {
        JSONObject o = fm.extractFromChildFile();
        ArrayList<JSONObject> arr = JSONHelper.getKeyArray(o);
        for (JSONObject x : arr) {
            children.add(mapToChildEntity(x));
        }
        Child[] childArr = new Child[children.size()];
        int count = 0;
        for (ChildEntity ce : children) {
            childArr[count] = Child.fromEntity(ce);
            count++;
        }
        return childArr;
    }

    public Child get(int ID) {
        return Child.fromEntity(getEntity(ID));
    }

    public ChildEntity getEntity(int ID) {
        JSONObject o = fm.extractFromChildFile(ID);
        ChildEntity child = mapToChildEntity(o);

        return getFromHashSet(child);
    }

    public Child edit(int ID, JSONObject update) {
        JSONObject old = fm.extractFromChildFile(ID);
        if (update.has("name")) old.put("name", update.getString("name"));
        if (update.has("birthday")) old.put("birthday", update.getString("birthday"));
        if (update.has("parentIds")) old.put("parentIds", update.getJSONArray("parentIds"));

        children.remove(get(ID));

        fm.writeToChildFile(ID, old);
        return Child.fromEntity(getFromHashSet(mapToChildEntity(old)));
    }

    public Child create(JSONObject json) {
        // TODO error handling
        if (!json.has("name") || !json.has("birthday")) return null;
        String name = json.getString("name"), birthday = json.getString("birthday");
        int ID = fm.getChildIndex() + 1;

        ChildEntity ce = new ChildEntity(name, birthday, ID, new int[0]);

        fm.writeToChildFile(ID, mapToJSON(ce));
        fm.writeToChildFile("index", ID);

        return Child.fromEntity(ce);
    }

    public boolean delete(int ID) {
        children.remove(get(ID));
        return fm.deleteFromChildFile(ID);
    }

    private ChildEntity getFromHashSet(ChildEntity parent) {
        if (children.contains(parent)) {
            for (ChildEntity p : children) {
                if (p.equals(parent)) return p;
            }
        }

        return parent;
    }

    private ChildEntity mapToChildEntity(JSONObject object) {
        // TODO add error handling
        String name = object.getString("name");
        String birthday = object.getString("birthday");
        int ID = object.getInt("ID");
        JSONArray parentArr = object.getJSONArray("parentIds");
        int[] parentIds = JSONHelper.toIntArray(parentArr);

        return new ChildEntity(name, birthday, ID, parentIds);
    }

    private JSONObject mapToJSON(ChildEntity child) {
        JSONObject json = new JSONObject();
        json.put("name", child.getName());
        json.put("birthday", child.getBirthday());
        json.put("ID", child.getID());
        json.put("parentIds", child._getParentIds());

        return json;
    }
}
