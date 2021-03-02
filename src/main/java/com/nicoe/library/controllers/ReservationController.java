package com.nicoe.library.controllers;

import com.nicoe.library.Services.BookService;
import com.nicoe.library.Services.ReservationService;
import com.nicoe.library.mapping.CreateReservation;
import com.nicoe.library.mapping.MyReservations;
import com.nicoe.library.model.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    BookService bookService;


    /**
     * List reservations of a user from user id
     * @param userId
     * @return myReservations
     */
    @GetMapping("/reservation/{userId}")
    public List<MyReservations> userReservationList(@PathVariable Integer userId) {
        List<MyReservations> myReservations = new ArrayList<>();
        List<Reservation> reservationList = reservationService.findReservation(userId);
        if (!reservationList.isEmpty()){
            for (int i=0;i<reservationList.size();i++){
                MyReservations reservation = new MyReservations();
                reservation.setReservationId(reservationList.get(i).getReservationId());
                reservation.setBookId(reservationList.get(i).getBook().getBookId());
                reservation.setTitle(reservationList.get(i).getBook().getTitle());
                reservation.setReturnDate(bookService.returnDate(reservationList.get(i).getBook().getBookId()));
                reservation.setRank(bookService.findRank(reservationList.get(i).getBook().getBookId(),userId));

                myReservations.add(reservation);
            }
        }
        return myReservations;
    }

    /**
     * Cancel reservation from reservation id
     * @param reservationId
     * @return message to confirm the cancellation of the reservation
     */
    @GetMapping("/cancel-reservation/{reservationId}")
    public String cancelReservation(@PathVariable Integer reservationId){
        reservationService.cancelReservation(reservationId);
        return "La réservations est annulée";
    }

    /**
     * Reserve a book
     * @param createReservation
     * @return createReservation in reservationService
     */
    @PostMapping("/reservation")
    public Reservation makeReservation(@RequestBody CreateReservation createReservation){
        return reservationService.createReserv(createReservation);
    }

}
