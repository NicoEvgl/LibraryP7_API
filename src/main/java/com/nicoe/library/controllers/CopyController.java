package com.nicoe.library.controllers;

import com.nicoe.library.Services.CopyService;
import com.nicoe.library.Services.UserService;
import com.nicoe.library.model.entities.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CopyController {

    @Autowired
    CopyService copyService;

    @Autowired
    UserService userService;

    /**
     * Extend Loan
     * @param copyId Integer Copy ID
     * @return copy
     */
    @GetMapping("/extend/{copyId}")
    public Copy extendLoan(@PathVariable Integer copyId){
        Copy copy = copyService.extendLoan(copyId);
        return copy;
    }

    /**
     * find loan List
     * @return loans
     */
    @GetMapping("/consult-loans")
    public List<Copy> consultLoans(){
        List<Copy> loans = copyService.loanList();
        return loans;
    }

    /**
     * Display user's loans
     * @param userId
     * @return loans
     */
    @GetMapping("/consult-loans/{userId}")
    public List<Copy> consultMyLoans(@PathVariable Integer userId){
        List<Copy> loans = copyService.myLoans(userId);
        return loans;
    }

    @GetMapping("/return-loan/{copyId}")
    public String returnLoan(@PathVariable Integer copyId){
        copyService.returnLoan(copyId);

        return "L'exemplaire a été rendu";
    }

    @PostMapping("/borrow/{userId}")
    public String borrowCopy(@RequestBody Copy copy, @PathVariable Integer userId){
        copy.setUser(userService.findByUserId(userId));
        String borrowedCopy = "La date de retour de l'exemplaire est le : " + copyService.makeLoan(copy);
        return borrowedCopy;
    }

    @GetMapping("/loanLate-list")
    public List<String> listMail(){
        List<String> email =new ArrayList<>();
        email = copyService.listMail();
        return email;
    }
}
