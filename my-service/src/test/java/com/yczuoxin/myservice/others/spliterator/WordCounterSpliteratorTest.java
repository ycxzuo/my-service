package com.yczuoxin.myservice.others.spliterator;

import com.yczuoxin.myservice.util.spliterator.WordCounter;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounterSpliteratorTest {

    String SENTENCE = " Nel mezzo del cammin  di nostra  vita  mi   ritrovai in una  selva oscura  che la dritta via era   smarrita";

    Stream<Character> stream = IntStream.rangeClosed(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

    @Test
    void testWordCount() {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
    }

    @Test
    void testWordCountSpliterator() {

    }

}
