package com.roskildefrieboernehave.webapp.services;
import com.roskildefrieboernehave.webapp.DB.DBManager;
import com.roskildefrieboernehave.webapp.helpers.JSONHelper;
import com.roskildefrieboernehave.webapp.entities.ParentEntity;
import com.roskildefrieboernehave.webapp.helpers.ReturnEntity;
import com.roskildefrieboernehave.webapp.models.Parent;
import com.roskildefrieboernehave.webapp.utils.FileManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;


public class ParentService implements IService<Parent> {

    private HashSet<ParentEntity> parents = new HashSet<ParentEntity>();
    private DBManager parentDB = new DBManager("Parents.JSON");

    public static ParentService getInstance() {
        return instance;
    }
    private static ParentService instance = new ParentService();

    private ParentService() { }

    public ReturnEntity<Parent[]> getAll() {
        JSONObject o = parentDB.queryAll();
        ArrayList<JSONObject> arr = JSONHelper.getKeyArray(o);
        for (JSONObject x : arr) {
            parents.add(mapToEntity(x));
        }
        Parent[] parentArr = new Parent[parents.size()];
        int count = 0;
        for(ParentEntity pe : parents) {
            parentArr[count] = Parent.fromEntity(pe);
            count++;
        }
        return new ReturnEntity<>("Success", parentArr);
    }

    public ReturnEntity<Parent> get(int ID) {
        return new ReturnEntity<>("Success", Parent.fromEntity(getEntity(ID)));
    }

    public ParentEntity getEntity(int ID) {
        JSONObject o = parentDB.queryById(ID);
        ParentEntity parent = mapToEntity(o);

        return getFromHashSet(parent);
    }

    public ReturnEntity<Parent> edit(int ID, JSONObject update) {
        JSONObject old = parentDB.queryById(ID);
        if (update.has("name")) old.put("name", update.getString("name"));
        if (update.has("phone")) old.put("phone", update.getString("phone"));
        if (update.has("childrenIds")) old.put("childrenIds", update.getJSONArray("childrenIds"));

        if (update.has("phone") && !validatePhone(update.getString("phone"))) return new ReturnEntity<>("Invalid phone format", null);

        parents.remove(getEntity(ID));

        parentDB.write(ID, old);

        return new ReturnEntity<>("Success", Parent.fromEntity(getFromHashSet(mapToEntity(old))));
    }

    public ReturnEntity<Parent> create(JSONObject json) {
        // TODO Error handling
        if (!json.has("name") || !json.has("phone")) return new ReturnEntity<>("Missing name or phone", null);

        String name = json.getString("name"), phone = json.getString("phone");
        int ID = parentDB.getIndex() + 1;

        if (!validatePhone(phone)) return new ReturnEntity<>("Bad Phone", null);

        ParentEntity pe = new ParentEntity(name, phone, ID, new int[0]);

        parentDB.write(ID, mapToJSON(pe));
        parentDB.write("index", ID);

        return new ReturnEntity<>("Success", Parent.fromEntity(pe));
    }

    public ReturnEntity<Boolean> delete(int ID) {
        try {
            if (!parents.remove(getEntity(ID))) return new ReturnEntity<>("An unknown error occurred", null);
            return new ReturnEntity<>("Success", parentDB.delete(ID));
        } catch (Exception e) {
            if (e instanceof JSONException) return new ReturnEntity<>("Something went wrong: Parent with ID " + ID + " wasn't found" , null);

            System.out.println(e);
            return new ReturnEntity<>("Something went wrong: " + e.getMessage(), null);
        }
    }

    private boolean validatePhone(String phone) {
        return phone.matches("^[0-9]{8}$");
    }

    private ParentEntity getFromHashSet(ParentEntity parent) {
        if (parents.contains(parent)) {
            for (ParentEntity p : parents) {
                if (p.equals(parent)) return p;
            }
        }

        return parent;
    }

    private ParentEntity mapToEntity(JSONObject object) {
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
