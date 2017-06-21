package com.itibo.service;

import com.itibo.entity.Word;

public interface WordService {

    public void saveWord(Word word);

    public void deleteWord(Word word);

    public void alterStatistics(Integer id, Integer repeated, Integer score);
}
