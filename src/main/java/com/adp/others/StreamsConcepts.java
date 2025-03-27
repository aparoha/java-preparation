/*
Introduced in Java 8 that allows for functional-style operations on collections of objects.
It provides an abstraction to process sequences of elements, such as collections, in a declarative and concise manner.

A Stream is a sequence of elements that can be processed in parallel or sequentially. Streams allow you to perform operations
like filtering, mapping, and reducing on collections without modifying the underlying data structures. The key idea is to
separate the "what" (the operation you want to perform) from the "how" (the implementation details).

1. Streams are not data structures: They are abstractions for processing data.
2. Streams do not modify the underlying data: They are used to perform operations on data and return results, leaving the original collection unchanged.
3. Streams are designed for functional programming: They enable functional-style operations, such as filtering, mapping, and reducing.
4. Streams are lazy: Intermediate operations are not executed until a terminal operation is performed.

Streams can be created from various sources, such as collections, arrays, or I/O channels. Some common ways to create a stream:

-> From a collection (e.g., List, Set, etc.)
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream = list.stream();

-> From an array
        String[] array = {"a", "b", "c"};
        Stream<String> stream = Arrays.stream(array);

-> Using Stream.of()
        Stream<String> stream = Stream.of("a", "b", "c");
 */

package com.adp.others;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsConcepts {

    public void doStreamBasics() {
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream = list.stream();
    }
}
