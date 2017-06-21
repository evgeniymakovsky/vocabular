package com.itibo.controller;

import com.itibo.entity.User;
import com.itibo.entity.Word;
import com.itibo.service.RoleService;
import com.itibo.service.UserService;
import com.itibo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
@Component
public class UserController implements Serializable {

    @Autowired
    @ManagedProperty("#{UserService}")
    private UserService userService;

    @Autowired
    @ManagedProperty("#{WordService}")
    private WordService wordService;

    private String username;
    private List<Word> words;
    private Word word = new Word();
    private User user;
    private String userAccount;

//    public String user() {
//        setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        User user = userService.findByUserName(username);
//        words = user.getWords();
//        return "user.xhtml?faces-redirect=true";
//    }

    public void newWord() {
        user = userService.findByUserName(username);
        word.setResponsibleUser(user);
        word.setRepeated(0);
        word.setScore(0);
        wordService.saveWord(word);
        user = userService.findByUserName(username);
        words = user.getWords();
        word = new Word();
    }

    public void deleteWord(Word w) {
        System.out.println("deleteWord() - " + w.getEnglish());
        wordService.deleteWord(w);
        User user = userService.findByUserName(username);
        words = user.getWords();
        System.out.println("User's words");
        for (Word wo : words) {
            System.out.println(wo.getEnglish());
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Word> getWords() {
        user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        words = user.getWords();
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public WordService getWordService() {
        return wordService;
    }

    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserAccount() {
        userAccount = "user.xhtml?faces-redirect=true";
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
