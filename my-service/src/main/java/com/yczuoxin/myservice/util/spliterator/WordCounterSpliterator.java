package com.yczuoxin.myservice.util.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;

    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++)); // 处理当前字符串 - WordCounter:accumulate
        return currentChar < string.length(); // 如果还有字符，则继续处理
    }

    /**
     * @see java.util.stream.AbstractTask#compute()
     *
     * @return
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null; // 需要解析的 String 已经足够小，可以顺序处理
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) { // 试探拆分位置设定为要解析的 string
            if (Character.isWhitespace(string.charAt(splitPos))) { // 让拆分位置前进到下一个空格
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos)); // 创建一个新的 WordCounterSpliterator 来解析 string 从开始到拆分位置的部分
                currentChar = splitPos; // 将这个 WordCounterSpliterator 的起始位置设为拆分位置
                return spliterator; // 发现一个空格并创建了新的 Spliterator，所以退出循环
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
