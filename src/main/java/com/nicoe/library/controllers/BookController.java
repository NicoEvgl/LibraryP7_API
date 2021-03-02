package com.nicoe.library.controllers;

import com.nicoe.library.Services.BookCustomService;
import com.nicoe.library.Services.BookService;
import com.nicoe.library.Services.ReservationService;
import com.nicoe.library.Services.impl.BookFromCriteria;
import com.nicoe.library.mapping.BookSearchResult;
import com.nicoe.library.model.entities.Book;
import com.nicoe.library.model.entities.Copy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookCustomService bookCustomService;

    @Autowired
    ReservationService reservationService;

    static Logger logger = LogManager.getLogger(BookController.class);


    /**
     * Display Book List
     * @return books
     */
    @GetMapping("/book")
    public List<Book> bookList() {
        List<Book> books = bookService.bookList();
        return books;
    }

    /**
     * Display search results
     * @param bookFromCriteria search book by criteria
     * @return bookSearchResults
     */
    @PostMapping("/search-book")
    public List<BookSearchResult> bookSearchResults(@RequestBody BookFromCriteria bookFromCriteria) {
        logger.debug("BookController search-book");
        List<Book> books = bookCustomService.multiCriteriaBook(bookFromCriteria);
        List<BookSearchResult> bookSearchResults = new ArrayList<>();
        for(Book book:books){
            BookSearchResult bookSearchResult = new BookSearchResult();
            bookSearchResult.setTitle(book.getTitle());
            bookSearchResult.setNbAvailable((int) book.getCopies().stream().filter((Copy::getAvailable)).count());
            Date returnDate = bookService.returnDate(book.getBookId());
            if (returnDate != null){
                bookSearchResult.setExpectedReturnDate(returnDate);
            }
            bookSearchResult.setBookId(book.getBookId());
            bookSearchResult.setNbReservationsMade(reservationService.nbCurrentReservations(book.getBookId()));
            bookSearchResult.setNbReservationsPossible(bookService.nbAllowedReservations(book.getBookId()));
            bookSearchResults.add(bookSearchResult);
        }
        return bookSearchResults;
    }

    /**
     * find loans in late
     * @return copies
     */
    @GetMapping("/late-loan")
    public List<Copy> lateLoan(){
        logger.debug("BookController late-loan");
        List<Copy> copies = bookService.loanInLate();
        return copies;
    }
}
