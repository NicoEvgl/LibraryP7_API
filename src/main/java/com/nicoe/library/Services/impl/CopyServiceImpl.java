package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.CopyService;
import com.nicoe.library.Services.ReservationService;
import com.nicoe.library.model.dao.CopyDao;
import com.nicoe.library.model.dao.ReservationDao;
import com.nicoe.library.model.entities.Book;
import com.nicoe.library.model.entities.Copy;
import com.nicoe.library.model.entities.Reservation;
import com.nicoe.library.model.entities.User;
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
    @Autowired
    ReservationDao reservationDao;
    @Autowired
    ReservationService reservationService;

    @Override
    public Copy extendLoan(Integer copyId) {
        Copy copy = findCopyById(copyId);
        Date dayDate = Date.valueOf(LocalDate.now());
        assert copy != null;
        if (dayDate.compareTo(copy.getLoanEndDate()) <0){
            editDateAndExtension(copy);
            copy.setExtend(true);
            copyDao.save(copy);
        }
        return copy;
    }

    @Override
    public List<Copy> myLoans(Integer userId) {
        List<Copy> copies = copyDao.findAllByUser_UserId(userId);
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
        findReservationForRemove(copy.getUser(),copy.getBook());
        loanedCopy.setUser(copy.getUser());
        loanedCopy.setAvailable(false);
        loanedCopy.setExtend(false);
        editDateAndExtension(loanedCopy);
        copyDao.save(loanedCopy);
        return String.valueOf(loanedCopy.getLoanEndDate());
    }

    @Override
    public void returnLoan(Integer copyId) {
        Copy copy = findCopyById(copyId);
        assert copy != null;
        findReservationForAlert(copy.getBook());
        copy.setAvailable(true);
        copy.setExtend(false);
        copy.setUser(null);
        copy.setLoanStartDate(null);
        copy.setLoanEndDate(null);
        copyDao.save(copy);
    }

    @Override
    public List<String> listMail() {
        List<String> email = new ArrayList<>();
        List<Copy> copies = copyDao.findAllByLoanEndDateBefore(Date.valueOf(LocalDate.now()));
        for (int i = 0; i<copies.size();i++){
            String address = copies.get(i).getUser().getEmail();
            email.add(address);
        }
        return email;
    }

    private Copy editDateAndExtension(Copy copy) {
        if (copy.getLoanStartDate() == null){
            copy.setLoanStartDate(Date.valueOf(LocalDate.now()));
        }
        if (copy.getLoanEndDate() == null) {
            copy.setLoanEndDate(Date.valueOf(LocalDate.now().plusDays(28)));
        }else {
            copy.setLoanEndDate(Date.valueOf(copy.getLoanEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(28)));
        }
        return copy;
    }

    private Copy findCopyById(Integer copyId){
        Optional<Copy> copy = copyDao.findById(copyId);
        return copy.isPresent() ? copy.get() :null ;
    }

    private void findReservationForAlert(Book book){
        List<Reservation> reservationList = reservationDao.findAllByBook_BookId(book.getBookId());
        if (!reservationList.isEmpty()){
            Reservation reservation = reservationList.get(0);
            reservationService.sendMailToMember(reservation);
            reservation.setAlertDate(new java.util.Date());
            reservationDao.save(reservation);
        }
    }

    private void findReservationForRemove(User user, Book book){
        List<Reservation> reservationList = reservationDao.findAllByBook_BookIdOrderByReservationId(book.getBookId());
        if (reservationList.size() > 0){
            if (reservationList.get(0).getUser().getUserId().equals(user.getUserId())){
                reservationService.cancelReservation(reservationList.get(0).getReservationId());
            }
        }
    }
}
