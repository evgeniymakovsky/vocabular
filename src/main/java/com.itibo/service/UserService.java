package com.itibo.service;

import com.itibo.entity.User;
import com.itibo.entity.Word;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

/**
 * Created by Makovsky on 24.04.2017.
 */
public interface UserService {

    public void saveUser(User user);

    public void removeUser(User user);

    public User findByUserName(String username);

    public List<User> userList();

    public List<Word> retriveUserWords(String name);

    public void changePassword(String name, String newPassword);

    public void saveImage(String name, Blob image);
}
