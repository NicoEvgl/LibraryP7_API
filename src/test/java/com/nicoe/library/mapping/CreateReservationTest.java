package com.nicoe.library.mapping;

import org.junit.Assert;
import org.junit.Test;

public class CreateReservationTest {
    @Test
    public void createReservationTest() {
        CreateReservation createReservation = new CreateReservation();
        createReservation.setBookId(1);
        createReservation.setUserId(3);

        Assert.assertEquals(java.util.Optional.of(1).get(),createReservation.getBookId());
        Assert.assertEquals(java.util.Optional.of(3).get(),createReservation.getUserId());
    }
}
