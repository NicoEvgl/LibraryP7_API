package com.nicoe.library.Services;


import com.nicoe.library.mapping.CreateReservation;
import com.nicoe.library.model.entities.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findReservation(Integer userId);
    void cancelReservation(Integer reservationId);
    Reservation createReserv(CreateReservation createReservation);
    Integer nbCurrentReservations(Integer bookId);
}
