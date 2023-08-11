package com.yczuoxin.myservice.util.spliterator;

import lombok.Getter;

public class WordCounter {

    @Getter
    private final int counter;

    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this : // 都是空格，所以不需要变化
                    new WordCounter(counter, true); // 将最后一次空格标志位设置为 true
        } else {
            return lastSpace ?
                    new WordCounter(counter + 1, false) : // 都一次是空格，这次是单词，数量 +1
                    this; // 还在读词，不需要做操作
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(this.counter + wordCounter.counter, wordCounter.lastSpace);
    }

}
