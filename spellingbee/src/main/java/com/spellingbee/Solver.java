package com.spellingbee;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Solver {

    public static Solver fromWords(Words words) {
        return new Solver(words);
    }

    private final Words words;

    public Solver(Words words) {
        this.words = Objects.requireNonNull(words);
    }

    public List<String> solve(char requiredLetter, String otherLetters) {
        if (otherLetters.length() != 6) {
            throw new IllegalArgumentException("Must have 6 otherLetters, found " + otherLetters.length());
        } 
        HashSet<Character> allowedLetters = new HashSet<Character>();
        // todo: fill set

        return Collections.emptyList();
    }

    boolean isCandidateGood(char requiredLetter, Set<Character> allowedLetters, String candidate) {
        boolean foundRequired = false;
        boolean allAreAllowed = true;
        for (int i = 0; i < candidate.length(); i++) {
            char candidateLetter = candidate.charAt(i);
            if (candidateLetter == requiredLetter) {
                foundRequired = true;
            }
            if (!allowedLetters.contains(candidateLetter)) {
                allAreAllowed = false;
                break;
            }
        }
        return foundRequired && allAreAllowed;
    }

}
