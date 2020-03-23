package com.roskildefrieboernehave.webapp.DB;

import com.roskildefrieboernehave.webapp.utils.FileManager;
import org.json.JSONObject;

public class DBManager {
    private FileManager fm;

    public DBManager(String filePath) {
        fm = new FileManager(filePath);
    }

    private JSONObject readJSON(boolean includeIndex) {
        String allLines = fm.readAllLines();
        JSONObject json = new JSONObject(allLines);

        if (!includeIndex) json.remove("index");
        return json;
    }

    private JSONObject readJSON() {
        return readJSON(false);
    }

    private boolean writeJSON(JSONObject content) {
        return fm.writeAllLines(content.toString(8));
    }

    public int getIndex() {
        return readJSON(true).getInt("index");
    }

    public JSONObject queryAll() {
        return readJSON();
    }

    public JSONObject queryById(int ID) {
        return readJSON().getJSONObject(String.valueOf(ID));
    }

    public boolean delete(int ID) {
        JSONObject fullJSON = readJSON(true);
        fullJSON.remove(String.valueOf(ID));
        return writeJSON(fullJSON);
    }

    public boolean write(int ID, Object content) {
        return write(String.valueOf(ID), content);
    }

    public boolean write(String ID, Object content) {
        JSONObject fullJSON = readJSON(true);
        fullJSON.put(String.valueOf(ID), content);
        return writeJSON(fullJSON);
    }
}
