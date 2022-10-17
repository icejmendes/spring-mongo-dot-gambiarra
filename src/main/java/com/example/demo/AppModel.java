package com.example.demo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;

@Document(collection = "appmodel")
public class AppModel {

    @MongoId
    public String id;
    public Map<String, Object> data;

    private AppModel(Map<String, Object> data) {
        this.data = data;
    }

    public static AppModel createAppModel(Map<String, Object> data) {
        AppModel appModel = new AppModel(data);
        return appModel;
    }

    public AppModel(){}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
