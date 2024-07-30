package com.vanity;

import com.vanity.service.NumberToLettersMap;
import com.vanity.service.VanityDatabaseService;
import com.vanity.service.VanityDictionary;
import com.vanity.service.VanityGenerator;

import java.util.List;

public class VanityApp {
    public static void main(String[] args) {
        VanityGenerator vanityGenerator = new VanityGenerator(new NumberToLettersMap(), new VanityDictionary(), new VanityDatabaseService());
        List<String> vanityNumbers = vanityGenerator.saveVanityNumbers("+0000");
        vanityNumbers.stream().forEach(System.out::println);
    }
}


