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

    @GetMapping("/extend/{copyId}")
    public Copy extendLoan(@PathVariable Integer copyId){
        Copy copy = copyService.extendLoan(copyId);
        return copy;
    }

    @GetMapping("/consultLoans")
    public List<Copy> consultLoans(){
        List<Copy> loans = copyService.loanList();
        return loans;
    }

    @GetMapping("/consultLoans/{userId}")
    public List<Copy> consultLoansByUserId(@PathVariable Integer userId){
        List<Copy> loans = copyService.myLoans(userId);
        return loans;
    }

    @GetMapping("/returnLoan/{copyId}")
    public String returnLoan(@PathVariable Integer copyId){
        copyService.returnLoan(copyId);

        return "L'exemplaire a été rendu";
    }

    @PostMapping("/loanCopy/{userId}")
    public String loanCopy(@RequestBody Copy copy, @PathVariable Integer userId){
        copy.setUser(userService.findByUserId(userId));
        String retour = "La date de retour de l'exemplaire est le : " + copyService.makeLoan(copy);
        return retour;
    }

    @GetMapping("/loanInLate")
    public List<String> emailList(){
        List<String> email =new ArrayList<>();
        email = copyService.emailList();
        return email;
    }
}
