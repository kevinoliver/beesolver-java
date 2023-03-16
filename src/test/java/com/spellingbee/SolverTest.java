package com.spellingbee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SolverTest {
    
    @Test
    public void solvesThePuzzle() throws Exception {
        Dictionary dictionary = Dictionary.load(DictionaryTest.WORDS1);
        Puzzle puzzle = Puzzle.from('d', "ogselm");
        Solver solver = Solver.from(dictionary, puzzle);
        List<Result.Valid> solutions = solver.solve();
        assertEquals(List.of(Result.valid("dogs")), solutions);
    }
}
