package com.spellingbee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import org.junit.jupiter.api.Test;

public class DictionaryTest {

    private static final URL WORDS1 = DictionaryTest.class.getClassLoader().getResource("test1.txt");

    @Test
    public void containsFiltersOutShortWords() throws Exception {
        Dictionary dictionary = Dictionary.load(WORDS1);
        assertTrue(dictionary.contains("dogs"));
        assertFalse(dictionary.contains("cat"));
    }
    
}
