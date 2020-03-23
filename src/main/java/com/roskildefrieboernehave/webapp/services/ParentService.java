package com.roskildefrieboernehave.webapp.services;
import com.roskildefrieboernehave.webapp.helpers.JSONHelper;
import com.roskildefrieboernehave.webapp.entities.ParentEntity;
import com.roskildefrieboernehave.webapp.models.Parent;
import com.roskildefrieboernehave.webapp.utils.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;


public class ParentService implements IService<ParentEntity> {

    private HashSet<ParentEntity> parents = new HashSet<ParentEntity>();
    private FileManager fm = FileManager.getInstance();

    public static ParentService getInstance() {
        return instance;
    }
    private static ParentService instance = new ParentService();

    private ParentService() { }

    public Parent[] getAll() {
        JSONObject o = fm.extractFromParentFile();
        ArrayList<JSONObject> arr = JSONHelper.getKeyArray(o);
        for (JSONObject x : arr) {
            parents.add(mapToParentEntity(x));
        }
        Parent[] parentArr = new Parent[parents.size()];
        int count = 0;
        for(ParentEntity pe : parents) {
            parentArr[count] = Parent.fromEntity(pe);
            count++;
        }
        return parentArr;
    }

    public Parent get(int ID) {
        return Parent.fromEntity(getEntity(ID));
    }

    public ParentEntity getEntity(int ID) {
        JSONObject o = fm.extractFromParentFile(ID);
        ParentEntity parent = mapToParentEntity(o);

        return getFromHashSet(parent);
    }

    public Parent edit(int ID, JSONObject update) {
        JSONObject old = fm.extractFromParentFile(ID);
        if (update.has("name")) old.put("name", update.getString("name"));
        if (update.has("phone")) old.put("phone", update.getString("phone"));
        if (update.has("childrenIds")) old.put("childrenIds", update.getJSONArray("childrenIds"));

        parents.remove(getEntity(ID));

        fm.writeToParentFile(ID, old);

        return Parent.fromEntity(getFromHashSet(mapToParentEntity(old)));
    }

    public Parent create(JSONObject json) {
        // TODO Error handling
        if (!json.has("name") || !json.has("phone")) return null;

        String name = json.getString("name"), phone = json.getString("phone");
        int ID = fm.getParentIndex() + 1;

        ParentEntity pe = new ParentEntity(name, phone, ID, new int[0]);

        fm.writeToParentFile(ID, mapToJSON(pe));
        fm.writeToParentFile("index", ID);

        return Parent.fromEntity(pe);
    }

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

    private ParentEntity mapToParentEntity(JSONObject object) {
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
        json.put("childrenIds", parent._getChildrenIds());
        return json;
    }
}
