package com.nicoe.library.model.dao;

import com.nicoe.library.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation,Integer> {
    List<Reservation> findAllByUser_UserId(Integer userId);
    void deleteByReservationId(Integer reservationId);
    List<Reservation> findAllByBook_BookId(Integer bookId);
    List<Reservation> findAllByBook_BookIdOrderByReservationId(Integer bookId);
    List<Reservation> findAllByAlertDateBefore(Date date);
}
