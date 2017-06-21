package com.itibo.entity;

import javax.persistence.*;

import java.sql.Blob;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "word_id",
            unique = true, nullable = false)
    private Integer word_id;

    @Column(name = "english", nullable = false)
    private String english;

    @Column(name = "russian", nullable = false)
    private String russian;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User responsibleUser;

    @Column(name = "repeated", nullable = false)
    private Integer repeated;

    @Column(name = "score", nullable = false)
    private Integer score;

    public Integer getWord_id() {
        return word_id;
    }

    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public Integer getRepeated() {
        return repeated;
    }

    public void setRepeated(Integer repeated) {
        this.repeated = repeated;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
