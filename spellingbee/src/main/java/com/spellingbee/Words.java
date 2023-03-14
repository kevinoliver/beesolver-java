package com.spellingbee;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Words {

    private static final String DEFAULT_PATH = "/american-english-small";

    // todo: refactor for testing other dictionaries
    public static Words load() throws IOException {
        URI in;
        try {
            in = Words.class.getClassLoader().getResource(DEFAULT_PATH).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try (Stream<String> lines = Files.lines(Path.of(in))) {
            // todo: maybe not the right data structure 
            Set<String> words = lines
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
            return new Words(words);
        }
    }

    private final Set<String> words;

    private Words(Set<String> words) {
        this.words = Objects.requireNonNull(words);
    }
    
}
