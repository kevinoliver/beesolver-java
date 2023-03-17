package com.spellingbee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class DictionaryTest {

    static final URI WORDS1;
    static {
        try {
            URL resource = DictionaryTest.class.getClassLoader().getResource("test1.txt");
            WORDS1 = resource.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

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
