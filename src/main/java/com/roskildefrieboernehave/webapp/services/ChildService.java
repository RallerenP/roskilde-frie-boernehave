package com.roskildefrieboernehave.webapp.services;

import com.roskildefrieboernehave.webapp.DB.DBManager;
import com.roskildefrieboernehave.webapp.entities.ChildEntity;
import com.roskildefrieboernehave.webapp.helpers.JSONHelper;
import com.roskildefrieboernehave.webapp.helpers.ReturnEntity;
import com.roskildefrieboernehave.webapp.models.Child;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class ChildService implements IService<Child> {
    private HashSet<ChildEntity> children = new HashSet<>();
    private DBManager childDB = new DBManager("Children.JSON");

    private static ChildService instance = new ChildService();
    public static ChildService getInstance() { return instance; }
    private ChildService() {}


    public ReturnEntity<Child[]> getAll() {
        JSONObject o = childDB.queryAll();
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
        return new ReturnEntity<>("Success", childArr);
    }

    public ReturnEntity<Child> get(int ID) {
        return new ReturnEntity<>("Success",Child.fromEntity(getEntity(ID)));
    }

    public ChildEntity getEntity(int ID) {
        JSONObject o = childDB.queryById(ID);
        ChildEntity child = mapToChildEntity(o);

        return getFromHashSet(child);
    }

    public ReturnEntity<Child> edit(int ID, JSONObject update) {
        JSONObject old = childDB.queryById(ID);
        if (update.has("name")) old.put("name", update.getString("name"));
        if (update.has("birthday")) old.put("birthday", update.getString("birthday"));
        if (update.has("parentIds")) old.put("parentIds", update.getJSONArray("parentIds"));

        children.remove(getEntity(ID));

        if (!childDB.write(ID, old)) return null;
        return new ReturnEntity<>("Success", Child.fromEntity(getFromHashSet(mapToChildEntity(old))));
    }

    public ReturnEntity<Child> create(JSONObject json) {
        // TODO error handling
        if (!json.has("name") || !json.has("birthday")) return new ReturnEntity<>("Missing name or birthday", null);
        String name = json.getString("name"), birthday = json.getString("birthday");
        int ID = childDB.getIndex() + 1;

        if (!validateBirthday(birthday)) return new ReturnEntity<>("Invalid birthday format", null);

        ChildEntity ce = new ChildEntity(name, birthday, ID, new int[0]);

        childDB.write(ID, mapToJSON(ce));
        childDB.write("index", ID);

        return new ReturnEntity<>("Success", Child.fromEntity(ce));
    }

    public ReturnEntity<Boolean> delete(int ID) {
        children.remove(getEntity(ID));
        return new ReturnEntity<>("Success",childDB.delete(ID));
    }

    private boolean validateBirthday(String birthday) {
        return birthday.matches("^(?:(?:31(\\/)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
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
