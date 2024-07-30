package com.vanity.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberToLettersMapTest {

    @Test
    public void testShouldNotReplaceDigit1WithChar() {

        NumberToLettersMap numberToLettersMap = new NumberToLettersMap();
        List<String> letterCombinations = numberToLettersMap.getLetterCombinations("11111111");

        assertEquals(1, letterCombinations.size());
        assertTrue(letterCombinations.contains("11111111"));

    }

    @Test
    public void testShouldNotReplaceDigit0WithChar() {

        NumberToLettersMap numberToLettersMap = new NumberToLettersMap();
        List<String> letterCombinations = numberToLettersMap.getLetterCombinations("1000000");

        assertEquals(1, letterCombinations.size());
        assertTrue(letterCombinations.contains("1000000"));

    }

    @Test
    public void testReplace2WithItsPossibleCombinations() {

        NumberToLettersMap numberToLettersMap = new NumberToLettersMap();
        List<String> letterCombinations = numberToLettersMap.getLetterCombinations("2112111");

        assertTrue(letterCombinations.contains("A11B111"));
        assertTrue(letterCombinations.contains("B11C111"));
        assertTrue(letterCombinations.contains("C11A111"));
    }

    @Test
    public void testReplace2and3WithItsPossibleCombinations() {

        NumberToLettersMap numberToLettersMap = new NumberToLettersMap();
        List<String> letterCombinations = numberToLettersMap.getLetterCombinations("23111111");

        assertTrue(letterCombinations.contains("AD111111"));
        assertTrue(letterCombinations.contains("AE111111"));
        assertTrue(letterCombinations.contains("AF111111"));

        assertTrue(letterCombinations.contains("BD111111"));
        assertTrue(letterCombinations.contains("BE111111"));
        assertTrue(letterCombinations.contains("BF111111"));

        assertTrue(letterCombinations.contains("CD111111"));
        assertTrue(letterCombinations.contains("CE111111"));
        assertTrue(letterCombinations.contains("CF111111"));

    }

}