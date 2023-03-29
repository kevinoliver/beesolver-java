# Spelling Bee Solver

Solves the [New York Times Spelling Bee](https://www.nytimes.com/puzzles/spelling-bee).

Their dictionary is not the same as the one included so you may find missing words as well as extras.

## Usage

```
$ ./beesolver --help
Usage: beesolver [-hV] [--[no-]words-output] [--dict=DICTIONARY]
                 REQUIRED_LETTER OTHER_LETTERS
      REQUIRED_LETTER       The letter required in all words
      OTHER_LETTERS         The 6 other allowed letters
      --dict=DICTIONARY     Path to a custom dictionary file
  -h, --help                Show this help message and exit.
  -V, --version             Print version information and exit.
      --[no-]words-output   When set only the stats for the solution are output
```

## Requirements

* Java 17
* Maven 

## Development

Run all tests:

```
$ mvn test
```
