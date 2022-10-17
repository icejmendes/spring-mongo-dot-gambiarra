package com.example.demo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.Map.Entry;

public class BeforeInsertOnMongoTest {
    /*
    banco[
    {"key":"agencia", "value":"AG.123"},
    {"key":"conta", "value":"123"}
    ]
     */

    final Map<String, Object> request = new HashMap<>();
    final static String REGEX_MODEL_ONE = "^(.+?)";
    final static String REGEX_MODEL_TWO = "(.*?)\\.";

    final static String KEY = "key";
    final static String VALUE = "value";


    @BeforeEach
    void setup() {
        request.put("banco.agencia", "Ag. 1xxx");
        request.put("banco.conta", "CC.123");
        request.put("endereco.rua", "RUA ABC");
        request.put("contato.telefone", "123456789");
        request.put("nome", "bruno");
    }

    @Test
    void gambiarra() {
        Set<Entry<String, Object>> entries = request.entrySet();
        Map<String, List<Map<String, Object>>> response = new HashMap<String, List<Map<String, Object>>>();

        for (Entry<String, Object> entry : entries) {
            String[] arr = entry.getKey().split("\\.");
            if (arr.length > 1) {
                retainAllKeys(arr[0], entry, response);
            }
        }
        System.out.println(response.toString());
    }

    private void retainAllKeys(String field, Entry<String, Object> entry, Map<String, List<Map<String, Object>>> response) {
        if (response.containsKey(field)) {
            response.get(field).add(settingValue(entry));
            return;
        }
        List<Map<String, Object>> value = new ArrayList<>();
        value.add(settingValue(entry));
        response.put(field, value);
    }

    private static Map<String, Object> settingValue(Entry<String, Object> entry) {
        return Map.of(KEY, entry.getKey(), VALUE, entry.getValue());
    }
}
