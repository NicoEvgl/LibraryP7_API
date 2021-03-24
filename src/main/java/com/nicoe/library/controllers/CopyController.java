package com.nicoe.library.controllers;

import com.nicoe.library.Services.CopyService;
import com.nicoe.library.Services.UserService;
import com.nicoe.library.model.entities.Copy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    static Logger logger = LogManager.getLogger(CopyController.class);

    public CopyController(CopyService copyService) {

    }

    /**
     * Extend Loan
     * @param copyId Integer Copy ID
     * @return copy
     */
    @GetMapping("/extend/{copyId}")
    public Copy extendLoan(@PathVariable Integer copyId){
        logger.debug("CopyController extend");
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
        logger.debug("CopyController consult-loans");
        List<Copy> loans = copyService.myLoans(userId);
        return loans;
    }


    /**
     * Return loan
     * @param copyId
     * @return "L'exemplaire a été rendu"
     */
    @GetMapping("/return-loan/{copyId}")
    public String returnLoan(@PathVariable Integer copyId){
        copyService.returnLoan(copyId);

        return "L'exemplaire a été rendu";
    }


    /**
     * Make Loan
     * @param copy
     * @param userId
     * @return
     */
    @PostMapping("/borrow/{userId}")
    public String borrowCopy(@RequestBody Copy copy, @PathVariable Integer userId){
        logger.debug("CopyController borrow");
        copy.setUser(userService.findByUserId(userId));
        String borrowedCopy = "La date de retour de l'exemplaire est le : " + copyService.makeLoan(copy);
        return borrowedCopy;
    }


    /**
     * Display user's mails with loans in late
     * @return email
     */
    @GetMapping("/loanLate-list")
    public List<String> listMail(){
        return copyService.listMail();
    }
}
