package com.spellingbee;

import java.util.Objects;

public class Solver {

    public static Solver fromWords(Words words) {
        return new Solver(words);
    }

    private final Words words;

    private Solver(Words words) {
        this.words = Objects.requireNonNull(words);
    }

}
