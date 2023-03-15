package com.spellingbee;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Words {

    private static final String DEFAULT_PATH = "/american-english-small";

    public static Words load() throws IOException {
        return load(Words.class.getClassLoader().getResource(DEFAULT_PATH));
    }

    static Words load(URL wordFile) throws IOException {
        URI in;
        try {
            in = wordFile.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try (Stream<String> lines = Files.lines(Path.of(in))) {
            // todo: maybe not the right data structure 
            Set<String> words = lines
                .filter(word -> word.length() > 3)
                .collect(Collectors.toSet());
            return new Words(words);
        }
    }    

    private final Set<String> words;

    private Words(Set<String> words) {
        this.words = Objects.requireNonNull(words);
    }

    public boolean contains(String word) {
        return words.contains(word);
    }
    
}
