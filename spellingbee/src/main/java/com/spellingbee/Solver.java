package com.spellingbee;

import java.util.Objects;

public class Solver {

    public static Solver from(Dictionary dictionary) {
        return new Solver(dictionary);
    }

    private final Dictionary dictionary;

    private Solver(Dictionary words) {
        this.dictionary = Objects.requireNonNull(words);
    }

}
