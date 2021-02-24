package com.nicoe.library.model.entities;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    @Test
    public void userTest() {
        User user = new User();
        user.setEmail("email");
        user.setPseudo("pseudo");
        user.setLastName("nom");
        user.setFirstName("prénom");
        user.setPassword("password");

        Assert.assertEquals("email", user.getEmail());
        Assert.assertEquals("pseudo", user.getPseudo());
        Assert.assertEquals("nom", user.getLastName());
        Assert.assertEquals("prénom", user.getFirstName());
        Assert.assertEquals("password", user.getPassword());
    }
}
