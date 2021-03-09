package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.BatchService;
import com.nicoe.library.Services.ReservationService;
import com.nicoe.library.model.dao.ReservationDao;
import com.nicoe.library.model.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    ReservationDao reservationDao;
    @Autowired
    ReservationService reservationService;

    @Override
    public void refreshReservation() {
        List<Integer> listBookId = deleteExceededReservation();
        List<Reservation> listNewReservation = listNewReservation(listBookId);
        sendMail(listNewReservation);
        addAlertDate(listNewReservation);
    }

    @Transactional
    public List<Integer> deleteExceededReservation(){
        List<Integer> listBookId = new ArrayList<>();
        Date dayDate = java.sql.Date.valueOf(LocalDate.now().minusDays(2));
        List<Reservation> reservations = reservationDao.findAllByAlertDateBefore(dayDate);
        if (!reservations.isEmpty()) {
            for (int i=0; i<reservations.size();i++){
                listBookId.add(reservations.get(i).getBook().getBookId());
                reservationService.cancelReservation(reservations.get(i).getReservationId());
            }
        }
        return listBookId;
    }

    private List<Reservation> listNewReservation(List<Integer> listBookId) {
        List<Reservation> listNewReservation = new ArrayList<>();
        for (int i = 0;i<listBookId.size();i++) {
            List<Reservation> reservations = reservationDao.findAllByBook_BookIdOrderByReservationId(listBookId.get(i));
            listNewReservation.add(reservations.get(0));
        }
        return listNewReservation;
    }

    private void sendMail(List<Reservation> reservations) {
        for (int i = 0; i<reservations.size();i++) {
            reservationService.sendMailToMember(reservations.get(i));
        }
    }

    @Transactional
    public void addAlertDate(List<Reservation> reservations) {
        Date alertDate = new Date();
        for (int i=0;i<reservations.size();i++) {
            reservations.get(i).setAlertDate(alertDate);
            reservationDao.save(reservations.get(i));
        }
    }

}
