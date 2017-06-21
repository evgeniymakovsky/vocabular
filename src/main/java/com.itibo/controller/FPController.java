package com.itibo.controller;

import com.itibo.entity.User;
import com.itibo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.nio.charset.Charset;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import static java.nio.charset.Charset.*;

@ManagedBean
@SessionScoped
@Component
public class FPController {

    @Autowired
    @ManagedProperty("#{UserService}")
    private UserService userService;

    private String username;
    private String email;

    public String generateNewPassword(){
        User user = userService.findByUserName(username);
        if(user.getEmail().equals(email)){
            String generatedString = username + System.nanoTime()/13584213;
            System.out.println(generatedString);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String newPassword = passwordEncoder.encode(generatedString);
            userService.changePassword(username, newPassword);

            final String username = "evgeniymakovsky@gmail.com";
            final String password = "13584213qwe";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(username));
                message.setSubject("Testing Subject");
                message.setText("Dear Mail Crawler,"
                        + "\n\n No spam to my email, please! \n\n" + "Your new password:" + generatedString);

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return "login_signup.xhtml?status=success&faces-redirect=true";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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
}
