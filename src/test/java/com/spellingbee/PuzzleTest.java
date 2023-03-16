package com.spellingbee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PuzzleTest {
    
    @Test
    public void fromThrowsWhenOtherLettersDoesntHaveSixLetters() {
        assertThrows(IllegalArgumentException.class, () -> Puzzle.from('c', "12345"));
        assertThrows(IllegalArgumentException.class, () -> Puzzle.from('c', "1234567"));
    }

    @Test
    public void fromThrowsWithDuplicatesInOtherLetters() {
        assertThrows(IllegalArgumentException.class, () -> Puzzle.from('z', "abcdee"));
    }

    @Test
    public void fromThrowsWhenOtherLettersHasRequiredLetter() {
        assertThrows(IllegalArgumentException.class, () -> Puzzle.from('z', "abcdez"));
    }

    @Test
    public void isValidForValidWords() throws Exception {
        Puzzle puzzle = Puzzle.from('d', "ogselm");
        assertTrue(puzzle.isValid("dogs"));
        assertTrue(puzzle.isValid("doom"));
        assertTrue(puzzle.isValid("does"));
        assertTrue(puzzle.isValid("moods"));
    }

    @Test
    public void isNotValidForWordsMissingRequiredLetter() {
        Puzzle puzzle = Puzzle.from('d', "ogselm");
        assertFalse(puzzle.isValid("logs"));
    }

    @Test
    public void isNotValidForWordsContainingUnallowedLetters() {
        Puzzle puzzle = Puzzle.from('d', "ogselm");
        assertFalse(puzzle.isValid("deal"));
        assertFalse(puzzle.isValid("seal"));
    }
}
