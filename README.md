# Elements Time Tester

A Java command-line application to explore common collection operations and how to instrument execution time. This project includes **4 collection types**, **7 generated data types**, **4 test modes** and two output formats.

> **Important:** the current implementation has known benchmark-methodology errors. Its time readings are **not reliable evidence that one Java collection is faster than another**. See [Tests and limitations](#tests-and-numerical-results).

## Supported configuration

| Dimension | Available options |
|---|---|
| Collections | `ArrayList`, `LinkedList`, `HashSet`, `TreeSet` |
| Generated data | Integer, Double, Person, MyColor, Animal, Food, Car |
| Operations | ADD, SEARCH (`contains`), REMOVE (`removeAll`), ALL_OPERATIONS |
| Dataset sizes | 100, 500, 1,000, 10,000 or custom |
| Output | Console or CSV (`Results.csv`) |

Data generation uses Java Streams. The program selects a generator, collection and presenter, then measures operations using `System.nanoTime()`.

The TreeSet is constructed with a comparator based on `hashCode`; this is **not** a substitute for a domain-specific ordering, and hash collisions can affect its comparison semantics.

## Implementation choices

- Enums represent user-configurable data types, collections, test modes and output formats.
- A generic `DataGenerator` abstraction creates data for the selected type.
- `GenericTester` executes the requested operation and collects timing values.
- `ConsolePresenter` and `CSVPresenter` separate display from measurement logic.
- `TestResult` stores measured values.

All implementation is currently in [`src/S30436Project01.java`](src/S30436Project01.java). The original assignment specification is in [`Description.md`](Description.md).

## Build and run

Tested with **OpenJDK 21** (a JDK is needed, not just a Java runtime).

```bash
git clone https://github.com/KazimirMaliewich/ElementsTimeTester.git
cd ElementsTimeTester
mkdir -p out
javac -d out src/S30436Project01.java
java -cp out S30436Project01
```

The interactive prompts request the data type, collection, output format, test mode and dataset size. For example, entering `1`, `1`, `1`, `1`, `1` selects **Integer / ArrayList / Console / ADD / 100 elements**.

## Tests and numerical results

A separate audit ran **18 scenarios** against the unmodified source code on **12 November 2026** (OpenJDK 21):

| Outcome | Count |
|---|---:|
| Passing scenarios | **11** |
| Failing scenarios | **7** |
| Total scenarios | **18** |

Passing cases included basic ADD and ALL_OPERATIONS execution across all **4 collection types**, single-operation CSV output, unsupported collection option handling and the all-operations cleanup state.

Failing cases included zero/negative/non-numeric input handling, CSV format for ALL_OPERATIONS, unpopulated SEARCH and REMOVE workloads, and the incorrect order of search and removal. See the complete scenario table in [TESTING.md](TESTING.md).

### Why no speed-up percentages are reported

The current code runs SEARCH and REMOVE against empty collections in their standalone modes. In ALL_OPERATIONS it runs `ADD -> REMOVE -> SEARCH`, so the search happens **after** data is removed. Two timing fields are also swapped when constructing the result, and the CSV header can have fewer fields than the data rows.

These are reproducible correctness and methodology issues; numbers produced by this implementation should not be treated as scientifically meaningful benchmark comparisons.

## Planned improvements (not yet implemented)

1. Populate the collection before SEARCH/REMOVE tests; use equivalent starting states across collection types.
2. Perform searches before removal and fix the swapped timing fields.
3. Validate custom dataset sizes and the numeric input prompts.
4. Ensure CSV header and row fields match for every test mode.
5. Repeat comparable measurements after JVM warm-up, or use JMH; report Java version, dataset sizes, repetitions and median timing.

## Project scope

This project demonstrates Java collections, basic generics, abstraction through interfaces, CLI configuration and timing instrumentation. It is a learning project, and its documented limitations are part of the engineering follow-up.
