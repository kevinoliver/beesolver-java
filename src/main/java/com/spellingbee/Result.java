package com.spellingbee;

import java.util.Comparator;

public sealed interface Result {
 
    public static Valid pangram(String word) {
        return new Valid(word, true);
    }

    public static Valid valid(String word) {
        return new Valid(word, false);
    }

    public static final Comparator<Result> COMPARATOR = new Comparator<>() {
        public int compare(Result r1, Result r2) {
            return r1.word().compareTo(r2.word());
        }
    };
    
    public String word();

    public boolean isValid();

    public boolean isPangram();

    public record Valid(String word, boolean isPangram) implements Result {
        public boolean isValid() {
            return true;
        }
    }
    
    public record Invalid(String word) implements Result {
        public boolean isPangram() {
            return false;
        }
    
        public boolean isValid() {
            return false;
        }
    }
}
