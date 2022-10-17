package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@SpringBootApplication
public class DemoApplication {
    final static String KEY = "key";
    final static String VALUE = "value";
    @Autowired
    AppRepository appRepository;

    @PostMapping("/save")
    public void save(@RequestBody Map<String, Object> request) {
        Map<String, Object> data = (Map<String, Object>) request.get("data");

        var response = retainFieldsFromRequest(data);
        var saved = appRepository.save(AppModel.createAppModel(response));

//        System.out.println(response.toString());
//        System.out.println(saved.toString());
    }

    private List<Map<String, Object>> extracData(Map<String, Object> request) {
        Map<String, Object> data = (Map<String, Object>) request.get("data");

        return null;
    }

    private Map<String, Object> retainFieldsFromRequest(Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        Set<Map.Entry<String, Object>> entries = request.entrySet();

        for (Map.Entry<String, Object> entry : entries) {
            String[] arr = entry.getKey().split("\\.");

            if (arr.length > 1) {
                retainAllKeys(arr[0], entry, response);
            }
        }
        return response;
    }

    private void retainAllKeys(String field, Map.Entry<String, Object> entry, Object newMap) {
        var response = ((Map<String, List<Map<String, Object>>>) newMap);

        if (response.containsKey(field)) {
            response.get(field).add(settingValue(entry));
            return;
        }
        List<Map<String, Object>> value = new ArrayList<>();
        value.add(settingValue(entry));
        response.put(field, value);
    }

    private static Map<String, Object> settingValue(Map.Entry<String, Object> entry) {
        return Map.of(KEY, entry.getKey(), VALUE, entry.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

