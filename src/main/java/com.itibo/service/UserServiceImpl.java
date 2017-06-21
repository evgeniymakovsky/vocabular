package com.itibo.service;

import com.itibo.entity.User;
import com.itibo.entity.Word;
import com.itibo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user) {
        if(user==null){
            throw new IllegalStateException("User should not be null!");
        }
        repository.saveAndFlush(user);
    }

    public void removeUser(User user) {
        repository.delete(user);
    }

    public User findByUserName(String username) {

        return repository.findByName(username);
    }

    public List<User> userList() {
        return repository.findAll();
    }

    public List<Word> retriveUserWords(String name) {
        User user = repository.findByName(name);
        return user.getWords();
    }

    public void changePassword(String name, String newPassword) {
        repository.changePassword(name, newPassword);
    }

    public void saveImage(String name, Blob image) {
        repository.saveImage(name, image);
    }
}
