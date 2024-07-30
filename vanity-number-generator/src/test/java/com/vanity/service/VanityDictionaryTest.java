package com.vanity.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VanityDictionaryTest {

    @Test
    public void testShouldGive0WeightIfVanityNumberDoesNotMatchAny() {
        VanityDictionary vanityDictionary = new VanityDictionary();

        assertEquals(0, vanityDictionary.getWeight("DES111SWQJ"));
    }

    @Test
    public void testShouldGive1WeightIfVanityNumberMatchAnyFromDictionary() {
        VanityDictionary vanityDictionary = new VanityDictionary();

        assertEquals(1, vanityDictionary.getWeight("AES111CALL"));
    }

}