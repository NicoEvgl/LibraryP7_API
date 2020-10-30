package com.nicoe.library.controllers;

import com.nicoe.library.Services.BookCustomService;
import com.nicoe.library.Services.BookService;
import com.nicoe.library.Services.impl.BookFromCriterias;
import com.nicoe.library.mapping.BookSearchResult;
import com.nicoe.library.model.entities.Book;
import com.nicoe.library.model.entities.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookCustomService bookCustomService;

    @GetMapping("/bookSearch")
    public List<Book> bookList() {
        List<Book> books = bookService.bookList();
        return books;
    }

    @PostMapping("/bookSearchProcess")
    public List<BookSearchResult> bookSearchResults(@RequestBody BookFromCriterias bookFromCriterias) {
        List<Book> books = bookCustomService.multiCriteriaBook(bookFromCriterias);
        List<BookSearchResult> bookSearchResultList = new ArrayList<>();
        for(Book book:books){
            BookSearchResult bookSearchResult = new BookSearchResult();
            bookSearchResult.setTitle(book.getTitle());
            bookSearchResult.setNbAvailable(book.getCopies().stream().filter((Copy::getAvailable)).count());
            bookSearchResultList.add(bookSearchResult);
        }
        return bookSearchResultList;
    }

    @GetMapping("/delayedLoan")
    public List<Copy> delayedLoan(){
        List<Copy> copies = bookService.loanInLate();
        return copies;
    }
}
