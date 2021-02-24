package com.nicoe.library.mapping;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class MyReservationsTest {
    @Test
    public void myReservationsTest() {
        MyReservations myReservations = new MyReservations();
        Date returnDate = Date.valueOf(LocalDate.now());
        myReservations.setRank(1);
        myReservations.setTitle("Titre");
        myReservations.setBookId(3);
        myReservations.setReturnDate(returnDate);

        Assert.assertEquals(returnDate, myReservations.getReturnDate());
        Assert.assertEquals(java.util.Optional.of(1).get(),myReservations.getRank());
        Assert.assertEquals("Titre",myReservations.getTitle());
        Assert.assertEquals(java.util.Optional.of(3).get(),myReservations.getBookId());
    }
}
