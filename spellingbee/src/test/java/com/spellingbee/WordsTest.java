package com.spellingbee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import org.junit.jupiter.api.Test;

public class WordsTest {

    private static final URL WORDS1 = WordsTest.class.getClassLoader().getResource("test1.txt");

    @Test
    public void containsFiltersOutShortWords() throws Exception {
        Words words = Words.load(WORDS1);
        assertTrue(words.contains("dogs"));
        assertFalse(words.contains("cat"));
    }
    
}
