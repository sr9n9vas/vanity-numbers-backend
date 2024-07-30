package com.vanity.service;

import java.util.ArrayList;
import java.util.List;

public class VanityDictionary {
    public static final List<String> dictionary = new ArrayList<>();

    static {
        dictionary.add("ORDER");
        dictionary.add("FOOD");
        dictionary.add("OPEN");
        dictionary.add("SELECT");
        dictionary.add("CALL");
        dictionary.add("NOW");
        dictionary.add("US");
        dictionary.add("OK");
    }


    public int getWeight(String vanityNumber) {
        int weight = 0;
        for (String word : dictionary) {
            if (vanityNumber.contains(word)) {
                weight++;
            }
        }
        return weight;
    }
}
