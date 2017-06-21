package com.itibo.controller;

import com.itibo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Class SettingsController is managed bean for /views/settings.xhtml view.
 */
@ManagedBean
@SessionScoped
@Component
public class SettingsController {

    @Autowired
    @ManagedProperty("#{UserService}")
    private UserService userService;

    private String newPassword;
    private String newPasswordConfirm;
    private String settings;

    /**
     * Method changePassword invokes when user changes his own password.
     */
    public void changePassword() {
        if (newPassword != null && newPasswordConfirm != null) {
            if(newPassword.equals(newPasswordConfirm)) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(newPassword);
                userService.changePassword(SecurityContextHolder.getContext().getAuthentication().getName(), hashedPassword);
            }
            else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords don't match!", null));
        }
        else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please, fill both fields!", null));
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getSettings() {
        settings = "settings.xhtml?faces-redirect=true";
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }
}
