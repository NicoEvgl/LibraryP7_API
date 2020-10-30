package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.CopyService;
import com.nicoe.library.model.dao.CopyDao;
import com.nicoe.library.model.entities.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    CopyDao copyDao;

    @Override
    public Copy extendLoan(Integer id) {
        Copy copy = findCopyById(id);
        Date dayDate = Date.valueOf(LocalDate.now());
        if (dayDate.compareTo(copy.getLoanEndDate()) <0){
            copy = editDateAndExtension(copy);
            copy.setExtend(true);
            copyDao.save(copy);
        }
        return copy;
    }

    @Override
    public List<Copy> myLoans(Integer id) {
        List<Copy> copies = copyDao.findAllByUser_Id(id);
        return copies;
    }

    @Override
    public List<Copy> loanList() {
        List<Copy> copies = copyDao.findAllByAvailableFalse();
        return copies;
    }

    @Override
    public String makeLoan(Copy copy) {
        Copy loanedCopy = copyDao.findByCopyId(copy.getCopyId());
        loanedCopy.setUser(copy.getUser());
        loanedCopy.setAvailable(false);
        loanedCopy.setExtend(false);
        loanedCopy = editDateAndExtension(loanedCopy);
        copyDao.save(loanedCopy);
        return String.valueOf(loanedCopy.getLoanEndDate());
    }

    @Override
    public void returnLoan(Integer copyId) {
        Copy copy = findCopyById(copyId);
        copy.setAvailable(true);
        copy.setExtend(false);
        copy.setUser(null);
        copy.setLoanStarDate(null);
        copy.setLoanEndDate(null);
        copyDao.save(copy);
    }

    @Override
    public List<String> emailList() {
        List<String> email = new ArrayList<>();
        List<Copy> copies = copyDao.findAllByLoanEndDateBefore(Date.valueOf(LocalDate.now()));
        for (int i = 0; i<copies.size();i++){
            String adress = copies.get(i).getUser().getEmail();
            email.add(adress);
        }
        return email;
    }

    private Copy editDateAndExtension(Copy copy) {
        if (copy.getLoanStarDate() == null){
            copy.setLoanStarDate(Date.valueOf(LocalDate.now()));
        }
        if (copy.getLoanEndDate() == null) {
            copy.setLoanEndDate(Date.valueOf(LocalDate.now().plusDays(28)));
        }else {
            copy.setLoanEndDate(Date.valueOf(copy.getLoanEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(28)));
        }
        return copy;
    }

    private Copy findCopyById(Integer copyId){
        Optional<Copy> copy = Optional.ofNullable(copyDao.findByCopyId(copyId));
        return copy.isPresent() ? copy.get() :null ;
    }
}
