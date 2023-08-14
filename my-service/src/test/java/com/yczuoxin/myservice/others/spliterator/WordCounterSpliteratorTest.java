package com.yczuoxin.myservice.others.spliterator;

import com.yczuoxin.myservice.util.spliterator.WordCounter;
import com.yczuoxin.myservice.util.spliterator.WordCounterSpliterator;
import org.junit.jupiter.api.Test;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class WordCounterSpliteratorTest {

    String SENTENCE = " Nel mezzo del cammin  di nostra  vita  mi   ritrovai in una  selva oscura  che la dritta via era   smarrita ";

    @Test
    void testWordCount() {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        Stream<Character> stream2 = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        WordCounter parallelWordCounter = stream2.parallel().reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println("wordCounter: " + wordCounter.getCounter());
        System.out.println("parallelWordCounter: " + parallelWordCounter.getCounter());
    }

    @Test
    void testWordCountSpliterator() {
        Spliterator<Character> wordCounterSpliterator = new WordCounterSpliterator(SENTENCE);
        WordCounter wordCounter = StreamSupport.stream(wordCounterSpliterator, true).reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println("testWordCountSpliterator: " + wordCounter.getCounter());
    }

}
