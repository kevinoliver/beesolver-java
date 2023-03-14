package com.spellingbee;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝");
        System.out.println("Hello and welcome to Spelling Bee Solver");
        System.out.println("🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝🐝");

        Words words = Words.load();
        Solver solver = Solver.fromWords(words);
    }
}
