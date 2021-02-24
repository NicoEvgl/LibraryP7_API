package com.nicoe.library.model.entities;

import org.junit.Assert;
import org.junit.Test;

public class BookTest {
    @Test
    public void bookTest() {
        Book book = new Book();
        book.setTitle("Titre");
        book.setAuthor("Auteur");

        Assert.assertEquals("Titre", book.getTitle());
        Assert.assertEquals("Auteur", book.getAuthor());
    }
}
