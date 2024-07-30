package com.vanity.service;

import com.vanity.exception.NotAValidPhoneNumberException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VanityGenerator {

    private NumberToLettersMap numberToLettersMap;
    private VanityDictionary vanityDictionary;
    private VanityDatabaseService vanityDatabaseService;

    public VanityGenerator(NumberToLettersMap numberToLettersMap, VanityDictionary vanityDictionary, VanityDatabaseService vanityDatabaseService) {
        this.numberToLettersMap = numberToLettersMap;
        this.vanityDictionary = vanityDictionary;
        this.vanityDatabaseService = vanityDatabaseService;
    }

    public List<String> saveVanityNumbers(String phoneNumber) {
        String phoneRegex = "^\\+?\\d{5,}";

        if (phoneNumber == null) {
            throw new NotAValidPhoneNumberException("Phone Number not provided");
        }
        if (!phoneNumber.matches(phoneRegex)) {
            throw new NotAValidPhoneNumberException("Phone Number doesn't match criteria");
        }
        if (phoneNumber.startsWith("+")) {
            phoneNumber = phoneNumber.substring(1);
        }
        List<String> letterCombinations = numberToLettersMap.getLetterCombinations(phoneNumber);
        Map<String, Integer> vanityPriorityMap = new HashMap<>();
        letterCombinations.stream().forEach(vanityNumber -> vanityPriorityMap.put(vanityNumber, vanityDictionary.getWeight(vanityNumber)));
        List<String> topVanityNumbers = getTopVanityNumbers(vanityPriorityMap);
        vanityDatabaseService.save(topVanityNumbers, phoneNumber);
        return topVanityNumbers;
    }

    private List<String> getTopVanityNumbers(Map<String, Integer> vanityPriorityMap) {
        List<Map.Entry<String, Integer>> sortedList = vanityPriorityMap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5).collect(Collectors.toList());
        return sortedList.stream().map(entry -> entry.getKey()).collect(Collectors.toList());
    }


}
