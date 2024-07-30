package com.vanity.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class NumberToLettersMap {
    private static final HashMap<Character, String> numberLettersMap = new HashMap<>();

    static {
        numberLettersMap.put('2', "ABC");
        numberLettersMap.put('3', "DEF");
        numberLettersMap.put('4', "GHI");
        numberLettersMap.put('5', "JKL");
        numberLettersMap.put('6', "MNO");
        numberLettersMap.put('7', "PQRS");
        numberLettersMap.put('8', "TUV");
        numberLettersMap.put('9', "WXYZ");
    }

    public List<String> getLetterCombinations(String phoneNumber) {
        List<String> letterCombinations = new ArrayList<>();
        letterCombinations.add(phoneNumber);
        return replace(letterCombinations, phoneNumber, 0);
    }

    private List<String> replace(List<String> replaceNumbers, String phoneNumber, int index) {
        if (index == phoneNumber.length()) {
            return replaceNumbers;
        }
        char digit = phoneNumber.charAt(index);
        List<String> numberReplacedList;
        if (digit == 49 || digit == 48) {
            numberReplacedList = new ArrayList<>();
            numberReplacedList.addAll(replaceNumbers);
        } else {
            numberReplacedList = replaceNumbers.stream().map(pNumber -> replaceNumber(pNumber, digit)).flatMap(list -> list.stream()).collect(Collectors.toList());
        }
        return replace(numberReplacedList, phoneNumber, index + 1);
    }

    private List<String> replaceNumber(String phoneNumber, char number) {
        String letters = numberLettersMap.get(number);
        List<String> replacedNumbers = Arrays.stream(letters.split("")).map(letter -> phoneNumber.replaceFirst(Character.toString(number), letter)).collect(Collectors.toList());
        return replacedNumbers;
    }

}
