package com.roskildefrieboernehave.webapp.services;

import com.roskildefrieboernehave.webapp.entities.ChildEntity;
import com.roskildefrieboernehave.webapp.helpers.JSONHelper;
import com.roskildefrieboernehave.webapp.utils.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class ChildService implements IService<ChildEntity> {
    private HashSet<ChildEntity> children = new HashSet<>();
    private FileManager fm = FileManager.getInstance();

    @Override
    public ChildEntity[] getAll() {
        JSONObject o = fm.extractFromChildFile();
        ArrayList<JSONObject> arr = JSONHelper.getKeyArray(o);
        for (JSONObject x : arr) {
            children.add(mapToChild(x));
        }
        ChildEntity[] childArr = new ChildEntity[children.size()];
        children.toArray(childArr);
        return childArr;
    }

    @Override
    public ChildEntity get(int ID) {
        JSONObject o = fm.extractFromChildFile(ID);
        ChildEntity child = mapToChild(o);

        return getFromHashSet(child);
    }

    @Override
    public ChildEntity edit(int ID, JSONObject update) {
        JSONObject old = fm.extractFromChildFile(ID);
        if (update.has("name")) old.put("name", update.getString("name"));
        if (update.has("birthday")) old.put("birthday", update.getString("birthday"));

        children.remove(get(ID));

        fm.writeToChildFile(ID, old);
        return getFromHashSet(mapToChild(old));
    }

    @Override
    public ChildEntity create(JSONObject json) {
        // TODO error handling
        if (!json.has("name") || !json.has("birthday")) return null;
        String name = json.getString("name"), birthday = json.getString("birthday");
        int ID = fm.getChildIndex() + 1;

        ChildEntity ce = new ChildEntity(name, birthday, ID, new int[0]);

        fm.writeToChildFile(ID, mapToJSON(ce));
        fm.writeToChildFile("index", ID);

        return ce;
    }

    @Override
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

    private ChildEntity mapToChild(JSONObject object) {
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
        json.put("parentIds", child.getParentIds());

        return json;
    }
}
