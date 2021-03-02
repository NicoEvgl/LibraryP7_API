package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.BookService;
import com.nicoe.library.model.dao.BookDao;
import com.nicoe.library.model.dao.CopyDao;
import com.nicoe.library.model.dao.ReservationDao;
import com.nicoe.library.model.entities.Book;
import com.nicoe.library.model.entities.Copy;
import com.nicoe.library.model.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;
    @Autowired
    CopyDao copyDao;
    @Autowired
    ReservationDao reservationDao;

    @Override
    public List<Book> bookList() {
        List<Book> books = bookDao.findAll();
        return books;
    }

    @Override
    public Integer findAvailableBook(Integer bookId) {
        Integer nbAvailable = copyDao.findAllByBook_BookIdAndAvailableTrue(bookId).size();
        return nbAvailable;
    }

    @Override
    public List<Copy> loanInLate() {
        Date date = new Date();
        List<Copy> copies = copyDao.findAllByLoanEndDateBefore(date);
        return copies;
    }

    @Override
    public Integer nbAllowedReservations(Integer bookId) {
        return bookDao.findAllByBookId(bookId).getCopies().size() * 2;
    }

    @Override
    public Date returnDate(Integer bookId) {
        List<Copy> copies = copyDao.findAllByBook_BookIdOrderByLoanEndDate(bookId);
        Date endDate = null;
        if (!copies.isEmpty()) {
            endDate = copies.get(0).getLoanEndDate();
        }
        return endDate;
    }

    @Override
    public Integer findRank(Integer bookId, Integer userId) {
        List<Reservation> reservations = reservationDao.findAllByBook_BookIdOrderByReservationId(bookId);
        int rank = 1;
        for (Reservation reservation : reservations) {
            if (reservation.getUser().getUserId().equals(userId)) {
                rank = rank + 1;
            }
            break;
        }
        return rank;
    }
}
