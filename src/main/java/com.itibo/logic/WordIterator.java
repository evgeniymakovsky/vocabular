package com.itibo.logic;

import com.itibo.entity.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordIterator {

    private final Random random;
    private final List<Word> words;

    public WordIterator(final List<Word> words) {
        this.words = new ArrayList<Word>(words);
        this.random = new Random();
    }

    public Word nextWord() {
        final int index = random.nextInt(words.size());
        final Word word = words.get(index);
        words.remove(index);
        return word;
    }
}
