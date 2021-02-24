package com.nicoe.library.model.entities;

import org.junit.Assert;
import org.junit.Test;

public class LibraryTest {
    @Test
    public void libraryTest() {
       Library library = new Library();
       library.setLibraryId(1);
       library.setLocation("lieu");

        Assert.assertEquals(java.util.Optional.of(1).get(), library.getLibraryId());
        Assert.assertEquals("lieu", library.getLocation());
    }
}
