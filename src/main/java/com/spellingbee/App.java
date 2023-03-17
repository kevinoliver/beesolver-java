package com.spellingbee;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name = "beesolver", version = "Beesolver 1.0", mixinStandardHelpOptions = true) 
public class App implements Callable<Integer> {
    
    @Spec 
    CommandSpec spec; // injected by picocli

    @Parameters(index = "0", paramLabel = "REQUIRED_LETTER", description = "The letter required in all words")
    private char required;

    @Parameters(index = "1", paramLabel = "OTHER_LETTERS", description = "The 6 other allowed letters")
    private String others;

    @Option(names = "--dict", paramLabel = "DICTIONARY", description = "Path to a custom dictionary file")
    private File dictionaryFile;

    @Option(names = "--words-output", 
        negatable = true,
        defaultValue = "true", 
        fallbackValue = "true",
        description = "Default on. When off, the solution's words are hidden")
    private boolean wordsOutput;

    private void validate() {
        if (others.length() != 6) {
            throw new ParameterException(spec.commandLine(),
                "OTHER_LETTERS must be exactly 6 letters, found: " + others.length());
        }
        HashSet<Character> letters = new HashSet<Character>();
        for (int i = 0; i < others.length(); i++) {
            char letter = others.charAt(i); 
            if (!letters.add(letter)) {
                throw new ParameterException(spec.commandLine(),
                    "OTHER_LETTERS cannot have duplicate letters, found: '" + letter + "'");
            }
        }
        if (letters.contains(required)) {
            throw new ParameterException(spec.commandLine(),
                "OTHER_LETTERS cannot contain the REQUIRED_LETTER: '" + required + "'");
        }
        if (dictionaryFile != null && !dictionaryFile.exists()) {
            throw new ParameterException(spec.commandLine(), "DICTIONARY not found: '" + dictionaryFile + "'");
        }
    }

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new App()).execute(args); 
        System.exit(exitCode); 
    }

    @Override
    public Integer call() throws IOException { 
        validate();

        System.err.println("🐝");
        System.err.println("Hello and welcome to Spelling Bee Solver");
        System.err.println("🐝🐝");

        Puzzle puzzle = Puzzle.from(required, others);
        Dictionary dictionary = dictionaryFile == null
            ? Dictionary.load()
            : Dictionary.load(dictionaryFile.toURI());
        Solver solver = Solver.from(dictionary, puzzle);

        System.err.println("🐝🐝🐝");
        System.err.println("Required Letter: " + required);
        System.err.println("Other Letters:   " + others);
        if (dictionaryFile != null) {
            System.err.println("Dictionary:      " + dictionaryFile);
        }
        System.err.println("Solving now");
        System.err.println("🐝🐝🐝🐝");

        List<Result.Valid> solutions = solver.solve();
        
        System.err.println("🐝🐝🐝🐝🐝");
        System.err.println("Solved!");
        System.err.println();
        System.err.println("  Words: " + solutions.size());
        System.err.println("  Pangrams: " + solutions.stream().filter(Result.Valid::isPangram).count());
        System.err.println("🐝🐝🐝🐝🐝🐝");

        if (wordsOutput) {
            for (Result.Valid solution : solutions) {
                if (solution.isPangram()) {
                    System.out.println(solution.word() + " 🍳");
                } else {
                    System.out.println(solution.word());
                }
            }
        }
        return 0;
    }

}
