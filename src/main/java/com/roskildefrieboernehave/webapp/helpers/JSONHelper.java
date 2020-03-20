package com.roskildefrieboernehave.webapp.helpers;

import org.json.JSONObject;

import java.util.ArrayList;

public class JSONHelper {

    public static ArrayList<JSONObject> getKeyArray(JSONObject object) {
        //TODO possibly convert to simple array
        ArrayList<JSONObject> arr = new ArrayList<>();
        object.keys().forEachRemaining(key->{
            arr.add((JSONObject)object.get(key));
        });
        return arr;
    }

}
