package com.spellingbee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {

    /**
     * This dictionary comes from the Debian package `wamerican-large`.
     */
    private static final String DEFAULT_PATH = "american-english-large";

    public static Dictionary load() throws IOException {
        URL file = Dictionary.class.getClassLoader().getResource(DEFAULT_PATH);
        if (file == null) {
            throw new FileNotFoundException("Dictionary file not found: " + DEFAULT_PATH);
        }
        try {
            return load(file.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static Dictionary load(URI dictionaryFile) throws IOException {        
        try (Stream<String> lines = Files.lines(Path.of(dictionaryFile))) {
            // - Spelling Bee words must be 4 letters of more
            // - removing accents allows us to match on words like "éclair"
            // - Using a Set removes any duplicates
            Set<String> words = lines
                .filter(word -> word.length() > 3)
                .map(Dictionary::removeAccents)
                .collect(Collectors.toSet());
            return new Dictionary(words);
        }
    }    

    private final Set<String> words;

    private Dictionary(Set<String> words) {
        this.words = Objects.requireNonNull(words);
    }

    public Stream<String> allWords() {
        return words.stream();
    } 

    // Implementation from:
    // https://www.baeldung.com/java-remove-accents-from-text
    static String removeAccents(String word) {
        if (Normalizer.isNormalized(word, Normalizer.Form.NFKD)) {
            return word;
        }
        String normalized = Normalizer.normalize(word, Normalizer.Form.NFKD);
        return normalized.replaceAll("\\p{M}", "");
    }
    
}
