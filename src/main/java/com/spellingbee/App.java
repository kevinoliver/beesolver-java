package com.spellingbee;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class App {
    
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            printUsageAndExit();
        }
        char requiredLetter = requiredLetterOrExit(args[0]);
        String otherLetters = otherLettersOrExit(args[1], requiredLetter);

        System.err.println("🐝");
        System.err.println("Hello and welcome to Spelling Bee Solver");
        System.err.println("🐝🐝");

        Puzzle puzzle = Puzzle.from(requiredLetter, otherLetters);
        Dictionary dictionary = Dictionary.load();
        Solver solver = Solver.from(dictionary, puzzle);

        System.err.println("🐝🐝🐝");
        System.err.println("Required Letter: " + requiredLetter);
        System.err.println("Other Letters:   " + otherLetters);
        System.err.println("Solving now");
        System.err.println("🐝🐝🐝🐝");

        List<Result.Valid> solutions = solver.solve();
        
        System.err.println("🐝🐝🐝🐝🐝");
        System.err.println("Solved!");
        System.err.println();
        System.err.println("  Words: " + solutions.size());
        System.err.println("  Pangrams: " + solutions.stream().filter(Result.Valid::isPangram).count());
        System.err.println("🐝🐝🐝🐝🐝🐝");
        System.err.println();

        for (Result.Valid solution : solutions) {
            if (solution.isPangram()) {
                System.out.println(solution.word() + " 🍳");
            } else {
                System.out.println(solution.word());
            }
        }
    }

    private static char requiredLetterOrExit(String arg) {
        if (arg.length() != 1) {
            printUsageAndExit("required-letter must be a single letter, found: '" + arg + "'");
        }
        return arg.charAt(0);
    }

    private static String otherLettersOrExit(String arg, char requiredLetter) {
        if (arg.length() != 6) {
            printUsageAndExit("other-letters must be exactly 6 letters, found: '" + arg + "'");
        }
        HashSet<Character> letters = new HashSet<Character>();
        for (int i = 0; i < arg.length(); i++) {
            char letter = arg.charAt(i); 
            if (!letters.add(letter)) {
                printUsageAndExit("other-letters cannot have duplicate letters, found: '" + letter + "'");
            }
        }
        if (letters.contains(requiredLetter)) {
            printUsageAndExit("other-letters cannot contain the required-letter: '" + requiredLetter + "'");
        }
        return arg;
    }

    private static void printUsageAndExit() {
        printUsageAndExit(null);
    }

    private static void printUsageAndExit(String additionalMessage) {
        System.err.println("Usage: required-letter other-letters");
        System.err.println();
        System.err.println("    required-letter - must be a single letter");
        System.err.println("    other-letters   - must be 6 unique letters and cannot include required-letter");
        if (additionalMessage != null) {
            System.err.println();
            System.err.println(additionalMessage);
        }
        System.exit(1);
    }
}
