package com.roskildefrieboernehave.webapp.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class FileManager {
    File file;

    public FileManager(String path) {
        file = new File(path);
    }

    public String readAllLines() {
        try {
            Scanner sc = new Scanner(file);
            String tmp = "";
            while (sc.hasNext()) {
                tmp += sc.next();
            }
            return tmp;
        } catch (FileNotFoundException e) { return null; }
    }

    public boolean writeAllLines(String content) {
        try {
            PrintStream ps = new PrintStream(file);
            ps.print(content);
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

//    private String childFilePath = "Children.JSON";
//    private String parentFilePath = "Parents.JSON";
//    private static FileManager instance = new FileManager();
//
//    public static FileManager getInstance() {
//        return instance;
//    }
//
//    private FileManager() {
//
//    }
//
//    private JSONObject readJSON(String filePath) {
//        return readJSON(filePath, false);
//    }
//
//    private JSONObject readJSON(String filePath, boolean index) {
//        try {
//            File file = new File(filePath);
//            Scanner scan = new Scanner(file);
//            String content = readAllLines(scan);
//            JSONObject jsonObject = new JSONObject(content);
//            if (!index) jsonObject.remove("index");
//            return jsonObject;
//        } catch(Exception e) {return null;}
//    }
//
//    private boolean writeJSON(String filePath, String content) {
//        try {
//            File file = new File (filePath);
//            PrintStream ps = new PrintStream(file);
//
//            ps.print(content);
//            return true;
//        } catch (Exception e) { return false; }
//    }
//
//    private String readAllLines(Scanner input) {
//        String tmp = "";
//        while (input.hasNext()) {
//            tmp += input.next();
//        }
//        return tmp;
//    }
//
//    public int getParentIndex() {
//        return readJSON(parentFilePath, true).getInt("index");
//    }
//    public int getChildIndex() { return readJSON(childFilePath, true).getInt("index"); }
//
//    public JSONObject extractFromParentFile() {
//        return readJSON(parentFilePath);
//    }
//    public JSONObject extractFromChildFile() { return readJSON(childFilePath); }
//
//    public JSONObject extractFromParentFile(int ID) {
//        JSONObject fullJson = readJSON(parentFilePath);
//        return fullJson.getJSONObject(String.valueOf(ID));
//    }
//
//    public JSONObject extractFromChildFile(int ID) {
//        JSONObject fullJson = readJSON(childFilePath);
//        return fullJson.getJSONObject(String.valueOf(ID));
//    }
//
//    public boolean deleteFromParentFile(int ID) {
//        JSONObject fullJSON = readJSON(parentFilePath, true);
//        fullJSON.remove(String.valueOf(ID));
//        return writeJSON(parentFilePath, fullJSON.toString());
//    }
//
//    public boolean deleteFromChildFile(int ID ) {
//        JSONObject fullJSON = readJSON(childFilePath, true);
//        fullJSON.remove(String.valueOf(ID));
//        return writeJSON(childFilePath, fullJSON.toString());
//    }
//
//    public boolean writeToParentFile(String ID, Object append) {
//        JSONObject full = readJSON(parentFilePath);
//        full.put(ID, append);
//        return writeJSON(parentFilePath, full.toString(4));
//    }
//
//    public boolean writeToChildFile(String ID, Object append) {
//        JSONObject full = readJSON(childFilePath);
//        full.put(ID, append);
//        return writeJSON(childFilePath, full.toString(4));
//    }
//
//    public boolean writeToParentFile(int ID, Object append) {
//        return writeToParentFile(String.valueOf(ID), append);
//    }
//
//    public boolean writeToChildFile(int ID, Object append) { return writeToChildFile(String.valueOf(ID), append); }
}
