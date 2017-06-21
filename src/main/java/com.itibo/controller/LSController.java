package com.itibo.controller;

import com.itibo.entity.Role;
import com.itibo.entity.User;
import com.itibo.service.RoleService;
import com.itibo.service.UserService;
import com.itibo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;

/**
 * Class LSController is managed bean for /login_signup.xhtml view.
 */
@ManagedBean
@SessionScoped
@Component
public class LSController implements Serializable {

    @Autowired
    @ManagedProperty("#{UserService}")
    private UserService userService;

    @Autowired
    @ManagedProperty("#{RoleService}")
    private RoleService roleDAO;

    private String username;

    private String email;

    private String password;

    /**
     * Method registerUser invokes when user registers himself in system.
     */
    public void registerUser() {
        if (userService.findByUserName(username) != null) {
            FacesContext.getCurrentInstance().addMessage("newUser:name", new FacesMessage("User with username " + username + " has already exists",
                    "User with username " + username + " has already exists"));
            return ;
        }
        User user = new User();
        user.setName(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        user.setEnabled(true);
        user.setEmail(email);
        userService.saveUser(user);

        Role role = new Role();
        role.setRole("ROLE_USER");
        role.setUser(user);
        roleDAO.saveRole(role);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public RoleService getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleService roleDAO) {
        this.roleDAO = roleDAO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
