package com.roskildefrieboernehave.webapp.utils;
import org.json.JSONObject;
import java.io.File;
import java.util.Scanner;

public class FileManager {

    private String childFilePath = "Children";
    private String parentFilePath = "Parents.JSON";
    private static FileManager instance = new FileManager();

    public static FileManager getInstance() {
        return instance;
    }

    private FileManager() {

    }

    public JSONObject readJSON(String filePath) {
        try {
            File file = new File(filePath);
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

    public JSONObject extractFromParentFile() {
        return readJSON(parentFilePath);

    }


}
