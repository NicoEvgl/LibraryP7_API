package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.BookService;
import com.nicoe.library.Services.ReservationService;
import com.nicoe.library.mapping.CreateReservation;
import com.nicoe.library.model.dao.BookDao;
import com.nicoe.library.model.dao.CopyDao;
import com.nicoe.library.model.dao.ReservationDao;
import com.nicoe.library.model.dao.UserDao;
import com.nicoe.library.model.entities.Book;
import com.nicoe.library.model.entities.Copy;
import com.nicoe.library.model.entities.Reservation;
import com.nicoe.library.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationServiceImpl implements ReservationService {

    private final JavaMailSender javaMailSender;


    @Autowired
    ReservationDao reservationDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CopyDao copyDao;
    @Autowired
    BookService bookService;

    public ReservationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public List<Reservation> findReservation(Integer userId) {
        return reservationDao.findAllByUser_UserId(userId);
    }

    @Override
    @Transactional
    public void cancelReservation(Integer reservationId) {
        reservationDao.deleteByReservationId(reservationId);
    }

    @Override
    public Reservation createReserv(CreateReservation createReservation) {
        Reservation reservation = new Reservation();
        boolean bookable = possibleReservation(createReservation);
        if (bookable) {
            Book book = findBook(createReservation.getBookId());
            User user = findUser(createReservation.getUserId());
            reservation.setBook(book);
            reservation.setUser(user);
            reservation.setReservationDate(new Date());
            reservationDao.save(reservation);
        }
        return reservation;
    }

    /**
     * Check that a user can reserve a book
     * @param createReservation
     * @return boolean
     */
    private Boolean possibleReservation(CreateReservation createReservation) {
        boolean isPossible = false;
        boolean bookable = bookableBook(createReservation);
        boolean availableCopy = notBorrow(createReservation);
        if (availableCopy && bookable) {
            isPossible = true;
        }
        return isPossible;
    }

    /**
     * Check  if we can reserve a book
     * @param createReservation
     * @return boolean
     */
    private Boolean bookableBook(CreateReservation createReservation) {
        boolean bookable = false;
        int nbPossibleReservations = bookService.nbAllowedReservations(createReservation.getBookId());
        int nbCurrentReservations = reservationDao.findAllByBook_BookId(createReservation.getBookId()).size();
        if (nbCurrentReservations < nbPossibleReservations) {
            bookable = true;
        }
        return bookable;
    }

    private Boolean notBorrow(CreateReservation createReservation) {
        boolean bookable = true;
        User user = findUser(createReservation.getUserId());
        List<Copy> copies = copyDao.findAllByBook_BookId(createReservation.getBookId());
        for (int i=0; i<copies.size(); i++) {
            if (copies.get(i).getUser() == null) {
                bookable = false;
                break;
            } else if (copies.get(i).getUser().getUserId().equals(createReservation.getUserId()) && !copies.get(i).getAvailable()) {
                bookable = false;
                break;
            }
        }
        return bookable;
    }

    private Book findBook(Integer bookId) {
        Optional<Book> book = bookDao.findById(bookId);
        return book.isPresent() ? book.get() : null;
    }

    private User findUser(Integer userId) {
        Optional<User> user = userDao.findById(userId);
        return user.isPresent() ? user.get() : null;
    }

    public Integer nbCurrentReservations(Integer bookId) {
        List<Reservation> reservations = reservationDao.findAllByBook_BookId(bookId);
        Integer nbCurrentReservations = reservations.size();
        return nbCurrentReservations;
    }

    public void sendMailToMember(Reservation reservation) {
        StringBuilder body = new StringBuilder();
        body.append("Cher Membre,\r\nUn exemplaire du livre que vous avez réserver est disponible\r\n")
                .append("\r\nLe livre concerné: ").append(reservation.getBook().getTitle()).append(".\r\n")
                .append("\r\nMerci de le récupérer dans les 48h.\r\n")
                .append("Passer ce délai le livre sera proposé à la personne suivante sur la liste.\r\n")
                .append("\r\nCordialement,\r\nLe gestionnaire de OCLibrary");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("admbiblioc@gmail.com");
        simpleMailMessage.setTo(reservation.getUser().getEmail());
        simpleMailMessage.setSubject("[OCLibrary] - Votre réservation est disponible");
        simpleMailMessage.setText(body.toString());
        javaMailSender.send(simpleMailMessage);
    }

}
