package com.itibo.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

//    @Id
//    @GeneratedValue
//    private long user_id;

    @Id
    @Column(name = "username", unique = true, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "responsibleUser")
    private List<Word> words;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Role> roles;

    @Column(name = "image")
    @Lob
    private Blob image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
