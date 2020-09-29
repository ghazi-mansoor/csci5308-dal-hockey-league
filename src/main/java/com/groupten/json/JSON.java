package com.groupten.json;

import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSON implements JSONInterface {
    @Override
    public boolean importJSONData(String path) {
        try{
            Object jsonData = new JSONParser().parse(new FileReader(path));
            System.out.println(jsonData);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean validateJSONData() {
        return false;
    }
}
