package com.spellingbee;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solver {

    public static Solver from(Dictionary dictionary, Puzzle puzzle) {
        return new Solver(dictionary, puzzle);
    }

    private final Dictionary dictionary;
    private final Puzzle puzzle;

    private Solver(Dictionary dictionary, Puzzle puzzle) {
        this.dictionary = Objects.requireNonNull(dictionary);
        this.puzzle = Objects.requireNonNull(puzzle);
    }

    public List<String> solve() {
        return dictionary.allWords()
            .filter(candidate -> puzzle.isValid(candidate))
            .sorted()
            .collect(Collectors.toList());
    }

}
