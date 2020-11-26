package com.nicoe.library.Services;

import com.nicoe.library.model.entities.Copy;

import java.util.List;

public interface CopyService {
    Copy extendLoan(Integer copyId);
    List<Copy> myLoans(Integer userId);
    List<Copy> loanList();
    String makeLoan(Copy copy);
    void returnLoan(Integer copyId);
    List<String> listMail();
}
