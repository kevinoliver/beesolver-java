package com.spellingbee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class DictionaryTest {

    static final URL WORDS1 = DictionaryTest.class.getClassLoader().getResource("test1.txt");

    @Test
    public void allWordsFiltersOutShortWords() throws Exception {
        Dictionary dictionary = Dictionary.load(WORDS1);
        Set<String> allWords = dictionary.allWords().collect(Collectors.toSet());
        assertTrue(allWords.contains("dogs"));
        assertFalse(allWords.contains("cat"));
    }

    @Test
    public void allWordsRemovesAccents() throws Exception {
        Dictionary dictionary = Dictionary.load(WORDS1);
        Set<String> allWords = dictionary.allWords().collect(Collectors.toSet());
        assertTrue(allWords.contains("eclair"));
        assertFalse(allWords.contains("éclair"));
    }
    
}
