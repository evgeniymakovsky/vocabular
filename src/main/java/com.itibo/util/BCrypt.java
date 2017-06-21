package com.itibo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Makovsky on 03.05.2017.
 */
public class BCrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String s = "55555";
        System.out.println(passwordEncoder.encode(s));
    }
}
