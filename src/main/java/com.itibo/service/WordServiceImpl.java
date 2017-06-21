package com.itibo.service;

import com.itibo.entity.Word;
import com.itibo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("WordService")
@Transactional
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository repository;

    public void saveWord(Word word) {
        repository.saveAndFlush(word);
    }

    public void deleteWord(Word word){
        repository.delete(word);
    }

    public void alterStatistics(Integer id, Integer repeated, Integer score) {
        repository.alterStatistics(id, repeated, score);
    }
}
