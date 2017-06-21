package com.itibo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LSControllerTest {

    @Autowired
    private LSController lsController;

    @Test
    public void testContextController(){

//        final UserService userService = lsController.getUserService();

//        assertNotNull(userService);
    }
}
