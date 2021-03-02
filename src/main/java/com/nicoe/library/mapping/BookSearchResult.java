package com.nicoe.library.mapping;

import java.util.Date;

public class BookSearchResult {
    String title;
    Integer nbAvailable;
    Date expectedReturnDate;
    Integer nbReservationsMade;
    Integer nbReservationsPossible;

    Integer bookId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNbAvailable() {
        return nbAvailable;
    }

    public void setNbAvailable(Integer nbAvailable) {
        this.nbAvailable = nbAvailable;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Integer getNbReservationsMade() {
        return nbReservationsMade;
    }

    public void setNbReservationsMade(Integer nbReservationsMade) {
        this.nbReservationsMade = nbReservationsMade;
    }

    public Integer getNbReservationsPossible() {
        return nbReservationsPossible;
    }

    public void setNbReservationsPossible(Integer nbReservationsPossible) {
        this.nbReservationsPossible = nbReservationsPossible;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
