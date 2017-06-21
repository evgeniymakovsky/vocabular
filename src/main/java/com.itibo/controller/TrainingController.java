package com.itibo.controller;

import com.itibo.entity.User;
import com.itibo.entity.Word;
import com.itibo.logic.WordIterator;
import com.itibo.service.UserService;
import com.itibo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Class TrainingController is managed bean for /views/training.xhtml view.
 */
@ManagedBean
@SessionScoped
@Component
public class TrainingController {

    @Autowired
    @ManagedProperty("#{UserService}")
    private UserService userService;

    @Autowired
    @ManagedProperty("#{WordService}")
    private WordService wordService;

    private Word next = new Word();
    private List<Word> words;
    private WordIterator iterator;
    private String checkWord;
    private String training = "training2.xhtml?faces-redirect=true";

    public void nextWord() {
        next = iterator.nextWord();
    }

    /**
     * Method invokes by Primefaces Timer when time out.
     */
    public void onTimeout() {
        int repeated = this.next.getRepeated();
        int score = this.next.getScore();
        System.out.println("onTimeout() - started / next.getRussian() - " + this.next.getRussian());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, next.getRussian(), null));
        wordService.alterStatistics(this.next.getWord_id(), repeated + 1, score);
        System.out.println("onTimeout() - finished");
    }

    /**
     * Method check invokes when user checks words during repeat.
     */
    public void check() {
        int repeated = this.next.getRepeated();
        int score = this.next.getScore();
        System.out.println("check() - started / checkWord - " + this.checkWord + " / next.getRussian() - " + this.next.getRussian());
        if (next.getRussian().equals(checkWord)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct!\n+1", null));
            wordService.alterStatistics(this.next.getWord_id(), repeated + 1, score + 1);
            System.out.println("true");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong!\n-1", null));
            wordService.alterStatistics(this.next.getWord_id(), repeated + 1, score);
            System.out.println("false");
        }

        System.out.println("check() - finished");
    }

    /**
     * Init method for TrainingController
     * @return URL training.xhtml
     */
    public String startTraining() {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        words = user.getWords();
        if (words != null) {
            iterator = new WordIterator(words);
        }
        return "training2.xhtml?faces-redirect=true";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public WordService getWordService() {
        return wordService;
    }

    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    public Word getNext() {
        return next;
    }

    public void setNext(Word next) {
        this.next = next;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public WordIterator getIterator() {
        return iterator;
    }

    public void setIterator(WordIterator iterator) {
        this.iterator = iterator;
    }

    public String getCheckWord() {
        return checkWord;
    }

    public void setCheckWord(String checkWord) {
        this.checkWord = checkWord;
    }

    public String getTraining() {
        training = this.startTraining();
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }
}
