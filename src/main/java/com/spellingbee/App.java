package com.spellingbee;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            printUsageAndExit();
        }
        // todo check the inputs (perhaps via methods in puzzle?)

        // todo mehhh
        char requiredLetter = args[0].charAt(0);
        String otherLetters = args[1];

        System.err.println("🐝");
        System.err.println("Hello and welcome to Spelling Bee Solver");
        System.err.println("🐝🐝");

        Puzzle puzzle = Puzzle.from(requiredLetter, otherLetters);
        Dictionary dictionary = Dictionary.load();
        Solver solver = Solver.from(dictionary, puzzle);

        System.err.println("🐝🐝🐝");
        System.err.println("Solving now");
        System.err.println("🐝🐝🐝🐝");

        List<String> solutions = solver.solve();
        
        System.err.println("🐝🐝🐝🐝🐝");
        System.err.println("Solved!");
        System.err.println("🐝🐝🐝🐝🐝🐝");
        System.err.println();

        for (String solution : solutions) {
            System.out.println(solution);
        }
    }

    private static void printUsageAndExit() {
        System.err.println("Usage: Spellingbee requiredLetter otherLetters");
        System.exit(1);
    }
}
