package com.nicoe.library.Services;

import com.nicoe.library.model.entities.Copy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CopyService {
    Copy extendLoan(Integer copyId);
    List<Copy> myLoans(Integer userId);
    List<Copy> loanList();
    String makeLoan(Copy copy);
    void returnLoan(Integer copyId);
    List<String> listMail();
}
