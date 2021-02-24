package com.nicoe.library.model.entities;

import com.nicoe.library.model.entities.Copy;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;


public class CopyTest {
    @Test
    public void copyTest(){
        Copy copy = new Copy();
        Date startDate = Date.valueOf(LocalDate.now());
        Date endDate = Date.valueOf(LocalDate.now());
        copy.setLoanStartDate(startDate);
        copy.setLoanEndDate(endDate);
        copy.setExtend(false);
        copy.setCopyId(1);

        Assert.assertEquals(java.util.Optional.of(1).get(), copy.getCopyId());
        Assert.assertEquals(startDate,copy.getLoanStartDate());
        Assert.assertEquals(endDate, copy.getLoanEndDate());
        Assert.assertFalse(copy.getExtend());
    }



}
