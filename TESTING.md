# Functional testing — Elements Time Tester

**Audit date:** 12 November 2026  
**Source:** original, unmodified source archive  
**Environment:** OpenJDK 21  
**Result:** **11 passed / 7 failed / 18 total**

These checks were created during an independent review; the current repository does not yet contain a repeatable benchmark/test suite.

| Scenario | Result | Observation |
|---|---|---|
| ArrayList / Integer / ADD 100 | PASS | Program finishes and reports ADD |
| ArrayList / ALL_OPERATIONS 100 | PASS | Timing metrics printed |
| LinkedList / Integer / ADD 100 | PASS | Program finishes and reports ADD |
| LinkedList / ALL_OPERATIONS 100 | PASS | Timing metrics printed |
| HashSet / Integer / ADD 100 | PASS | Program finishes and reports ADD |
| HashSet / ALL_OPERATIONS 100 | PASS | Timing metrics printed |
| TreeSet / Integer / ADD 100 | PASS | Program finishes and reports ADD |
| TreeSet / ALL_OPERATIONS 100 | PASS | Timing metrics printed |
| CSV / single operation | PASS | Header and row have four fields |
| CSV / ALL_OPERATIONS | **FAIL** | Four header fields, six data fields |
| Custom dataset size 0 | **FAIL** | `IndexOutOfBoundsException` |
| Custom dataset size -5 | **FAIL** | `IllegalArgumentException` |
| Unsupported collection number | PASS | Error message shown |
| Nonnumeric prompt input | **FAIL** | `InputMismatchException` |
| Standalone SEARCH with existing data | **FAIL** | `contains` executes on empty collection |
| Standalone REMOVE with existing data | **FAIL** | `removeAll` executes on empty collection |
| ALL_OPERATIONS searches before remove | **FAIL** | Actual order ADD, REMOVE, SEARCH |
| ALL_OPERATIONS clears collection | PASS | Final size 0 |

## Reproduce CLI behavior

Build as described in the main README, then run:

```bash
printf '1\n1\n1\n1\n1\n' | java -cp out S30436Project01
```

Selections: Integer, ArrayList, console, ADD, 100 elements. A timing number is printed, but a single run is not a valid comparative benchmark.

For a broken zero-size input, use `1`, `1`, `1`, `1`, `5`, `0` as successive entries.

## Root-cause notes

- `GenericTester.test()`'s SEARCH branch performs `contains` without populating the collection.
- Its REMOVE branch performs `removeAll` without inserting the dataset first.
- ALL_OPERATIONS runs `collection.addAll`, then `removeAll`, then `contains`.
- The `TestResult` call swaps the `removeTime` and `searchTime` parameters.
- CSV presenter writes four header columns but six values for ALL_OPERATIONS.

## What would constitute valid performance data?

1. Same generated dataset and equivalent operation across each collection.
2. Same initial collection contents before each measured operation.
3. JVM warm-up, repeated measurements, and timing summaries such as median and dispersion.
4. Clear definitions of the workload, element type and dataset size.
5. Validation that the operation actually modified or searched nonempty data.

**No comparative speed-up figures are currently claimed.**
