package com.itibo.controller;

import com.itibo.entity.User;
import com.itibo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Class AdminController is managed bean for /views/admin.xhtml view.
 */
@ManagedBean
@SessionScoped
@Component
public class AdminController {

    @Autowired
    @ManagedProperty("#{UserService}")
    private UserService userService;

    private List<User> users;
    private String admin;

    /**
     * Method deleteUser invokes when admin delete user from database
     * @param responsibleUser - user to delete
     */
    public void deleteUser(User responsibleUser) {
        userService.removeUser(responsibleUser);
        users = userService.userList();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUsers() {
        users = userService.userList();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getAdmin() {
        users = userService.userList();
        admin = "admin.xhtml?faces-redirect=true";
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
