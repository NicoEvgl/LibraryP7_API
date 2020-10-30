package com.nicoe.library.Services;

import com.nicoe.library.model.entities.Copy;

import java.util.List;

public interface CopyService {
    Copy extendLoan(Integer id);
    List<Copy> myLoans(Integer id);
    List<Copy> loanList();

    String makeLoan(Copy copy);

    void returnLoan(Integer id);
    List<String> emailList();
}
