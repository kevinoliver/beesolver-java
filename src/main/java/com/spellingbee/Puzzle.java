package com.spellingbee;

import java.util.HashSet;
import java.util.Set;

public class Puzzle {
    
    public static Puzzle from(char requiredLetter, String otherLetters) {
        if (otherLetters.length() != 6) {
            throw new IllegalArgumentException("Must have 6 otherLetters, found " + otherLetters.length());
        } 
        Set<Character> allowedLetters = new HashSet<Character>();
        for (int i = 0; i < otherLetters.length(); i++) {
            char ch = otherLetters.charAt(i);
            if (!allowedLetters.add(ch)) {
                throw new IllegalArgumentException("otherLetters cannot have any duplicates, found: '" + ch + "'");
            }
        }
        if (!allowedLetters.add(requiredLetter)) {
            throw new IllegalArgumentException("otherLetters cannot contain the requiredLetter: " + requiredLetter);
        }
        return new Puzzle(requiredLetter, allowedLetters);
    }

    private final char requiredLetter;
    private final Set<Character> allowedLetters;

    private Puzzle(char requiredLetter, Set<Character> allowedLetters) {
        this.requiredLetter = requiredLetter;
        this.allowedLetters = allowedLetters;
    }

    public Result resultFor(String candidate) {
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
        if (foundRequired && allAreAllowed) {
            HashSet<Character> uniqueLetters = new HashSet<Character>();
            for (int i = 0; i < candidate.length(); i++) {
                uniqueLetters.add(candidate.charAt(i));
            }
            boolean isPangram = uniqueLetters.size() == 7;
            return new Result.Valid(candidate, isPangram);
        } else {
            return new Result.Invalid(candidate);
        }
    }

}
