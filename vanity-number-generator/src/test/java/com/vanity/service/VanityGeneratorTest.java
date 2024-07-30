package com.vanity.service;

import com.vanity.exception.NotAValidPhoneNumberException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VanityGeneratorTest {

    @Test
    public void testShouldThrowExceptionIfPhoneNumberNotProvided() {
        NumberToLettersMap numberToLettersMap = Mockito.mock(NumberToLettersMap.class);
        VanityDictionary vanityDictionary = Mockito.mock(VanityDictionary.class);
        VanityDatabaseService vanityDatabaseService = Mockito.mock(VanityDatabaseService.class);
        VanityGenerator vanityGenerator = new VanityGenerator(numberToLettersMap, vanityDictionary, vanityDatabaseService);

        NotAValidPhoneNumberException exception = assertThrows(NotAValidPhoneNumberException.class, () -> vanityGenerator.saveVanityNumbers(null));
        assertEquals("Phone Number not provided", exception.getMessage());

    }

    @Test
    public void testShouldThrowExceptionIfNotValidPhoneNumber() {
        NumberToLettersMap numberToLettersMap = Mockito.mock(NumberToLettersMap.class);
        VanityDictionary vanityDictionary = Mockito.mock(VanityDictionary.class);
        VanityDatabaseService vanityDatabaseService = Mockito.mock(VanityDatabaseService.class);
        VanityGenerator vanityGenerator = new VanityGenerator(numberToLettersMap, vanityDictionary, vanityDatabaseService);

        NotAValidPhoneNumberException exception = assertThrows(NotAValidPhoneNumberException.class, () -> vanityGenerator.saveVanityNumbers("$7658766876"));
        assertEquals("Phone Number doesn't match criteria", exception.getMessage());

    }

    @Test
    public void testShouldGetLetterCombinationsForThePhoneNumber() {
        NumberToLettersMap numberToLettersMap = Mockito.mock(NumberToLettersMap.class);
        VanityDictionary vanityDictionary = Mockito.mock(VanityDictionary.class);
        VanityDatabaseService vanityDatabaseService = Mockito.mock(VanityDatabaseService.class);
        VanityGenerator vanityGenerator = new VanityGenerator(numberToLettersMap, vanityDictionary, vanityDatabaseService);

        String phoneNumber = "97112255";
        List<String> possibleCombinations = Arrays.asList("YS11AAKL","WP11CALL", "XR11CBJL", "YR11ABKL");

        Mockito.when(numberToLettersMap.getLetterCombinations(Mockito.eq(phoneNumber))).thenReturn(possibleCombinations);
        Mockito.when(vanityDictionary.getWeight("YS11AAKL")).thenReturn(0);
        Mockito.when(vanityDictionary.getWeight("WP11CALL")).thenReturn(1);
        Mockito.when(vanityDictionary.getWeight("XR11CBJL")).thenReturn(0);
        Mockito.when(vanityDictionary.getWeight("YR11ABKL")).thenReturn(0);

        List<String> vanityNumbers = vanityGenerator.saveVanityNumbers(phoneNumber);

        assertEquals(vanityNumbers.get(0), "WP11CALL");


    }

    @Test
    public void testShouldGetSamePhoneNumberWhenThereAreNoVanityNumbers() {
        NumberToLettersMap numberToLettersMap = Mockito.mock(NumberToLettersMap.class);
        VanityDictionary vanityDictionary = Mockito.mock(VanityDictionary.class);
        VanityDatabaseService vanityDatabaseService = Mockito.mock(VanityDatabaseService.class);
        VanityGenerator vanityGenerator = new VanityGenerator(numberToLettersMap, vanityDictionary, vanityDatabaseService);

        String phoneNumber = "11001100";
        List<String> possibleCombinations = Arrays.asList("11001100");

        Mockito.when(numberToLettersMap.getLetterCombinations(Mockito.eq(phoneNumber))).thenReturn(possibleCombinations);
        Mockito.when(vanityDictionary.getWeight("11001100")).thenReturn(0);

        List<String> vanityNumbers = vanityGenerator.saveVanityNumbers(phoneNumber);

        assertEquals(vanityNumbers.get(0), "11001100");


    }

}