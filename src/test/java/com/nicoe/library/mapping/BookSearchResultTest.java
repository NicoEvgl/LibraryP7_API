package com.nicoe.library.mapping;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class BookSearchResultTest {
    @Test
    public void bookSearchResultTest(){
        BookSearchResult bookSearchResult = new BookSearchResult();
        Date expectedReturnDate = Date.valueOf(LocalDate.now());

        bookSearchResult.setBookId(1);
        bookSearchResult.setTitle("Titre");
        bookSearchResult.setNbAvailable(4);
        bookSearchResult.setNbReservationsPossible(4);
        bookSearchResult.setNbReservationsMade(2);
        bookSearchResult.setExpectedReturnDate(expectedReturnDate);

        Assert.assertEquals(java.util.Optional.of(1).get(), bookSearchResult.getBookId());
        Assert.assertEquals("Titre", bookSearchResult.getTitle());
        Assert.assertEquals(java.util.Optional.of(4).get(), bookSearchResult.getNbAvailable());
        Assert.assertEquals(java.util.Optional.of(4).get(), bookSearchResult.getNbReservationsPossible());
        Assert.assertEquals(java.util.Optional.of(2).get(), bookSearchResult.getNbReservationsMade());
        Assert.assertEquals(expectedReturnDate, bookSearchResult.getExpectedReturnDate());

    }
}
