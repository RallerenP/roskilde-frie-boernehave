package com.roskildefrieboernehave.webapp.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Scanner;

public class FileManager {
    File file;

    public FileManager(String path) {
        file = new File(path);
    }

    public String readAllLines() {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            return null;
        }
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
}