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
        assertTrue(puzzle.resultFor("dogs").isValid());
        assertTrue(puzzle.resultFor("doom").isValid());
        assertTrue(puzzle.resultFor("does").isValid());
        assertTrue(puzzle.resultFor("moods").isValid());
    }

    @Test
    public void isNotValidForWordsMissingRequiredLetter() {
        Puzzle puzzle = Puzzle.from('d', "ogselm");
        assertFalse(puzzle.resultFor("logs").isValid());
    }

    @Test
    public void isNotValidForWordsContainingUnallowedLetters() {
        Puzzle puzzle = Puzzle.from('d', "ogselm");
        assertFalse(puzzle.resultFor("deal").isValid());
        assertFalse(puzzle.resultFor("seal").isValid());
    }

    @Test
    public void isPangramForPangrams() {
        Puzzle puzzle = Puzzle.from('d', "ogselm");
        assertTrue(puzzle.resultFor("dogselm").isPangram());
        assertTrue(puzzle.resultFor("dogselmm").isPangram());
        assertTrue(puzzle.resultFor("dogselmdogselm").isPangram());
    }

    @Test
    public void isPangramForNonPangrams() {
        Puzzle puzzle = Puzzle.from('d', "ogselm");
        assertFalse(puzzle.resultFor("dogsel").isPangram());
        assertFalse(puzzle.resultFor("dogsell").isPangram());
    }

}
