package com.itibo.service;

import com.itibo.entity.User;
import com.itibo.entity.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class WordServiceTest {

    @Autowired
    private WordService wordService;

    @Autowired
    private UserService userService;

    @Test
    public void saveWordTest_expect_ok(){
        User user = new User();
        user.setName("UserName");
        user.setPassword("Password");
        user.setEnabled(true);
        user.setEmail("user@user.com");
        userService.saveUser(user);
        Word word = new Word();
        word.setEnglish("hello");
        word.setRussian("Привет");
        word.setRepeated(0);
        word.setScore(0);
        word.setResponsibleUser(user);
        wordService.saveWord(word);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void saveWordTest_expect_InvalidDataAccessApiUsageException(){
        wordService.saveWord(null);
    }
}
