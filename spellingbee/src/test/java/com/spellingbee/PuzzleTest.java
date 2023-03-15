package com.spellingbee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PuzzleTest {
    
    @Test
    public void testFromNotEnoughLetters() {
        assertThrows(IllegalArgumentException.class, () -> Puzzle.from('c', "123"));
    }

    // todo: other from tests

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
