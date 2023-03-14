package com.spellingbee;

public class Solver {

    public static Solver fromWords(Words words) {
        return new Solver(words);
    }

    public Solver(Words words) {
        this.words = Objects.requireNonNull(words);
    }

}
