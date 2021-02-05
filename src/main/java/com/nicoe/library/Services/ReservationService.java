package com.nicoe.library.Services;

import com.nicoe.library.mapping.CreateReservation;
import com.nicoe.library.model.entities.Reservation;

import javax.transaction.Transactional;
import java.util.List;

public interface ReservationService {
    List<Reservation> findReservation(Integer userId);

    @Transactional
    void cancelReservation(Integer reservationId);

    Reservation createReservation(CreateReservation createReservation);
}
