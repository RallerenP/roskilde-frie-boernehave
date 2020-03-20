package com.roskildefrieboernehave.webapp.utils;

import org.json.JSONObject;

import java.io.File;
import java.util.Scanner;

public class FileManager {

    private String childFilePath = "Children";
    private String parentFilePath = "Parents.JSON";

    public JSONObject readJSON() {
        try {
            File file = new File(parentFilePath);
            Scanner scan = new Scanner(file);
            String content = readAllLines(scan);
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject;
        } catch(Exception e) {return null;}
    }

    public String readAllLines(Scanner input) {
        String tmp = "";
        while (input.hasNext()) {
            tmp += input.next();
        }
        return tmp;
    }

    public String[] extractFromParentFile() {
        JSONObject etEllerAndet = readJSON();

    }


}
