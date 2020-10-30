package com.nicoe.library.Services;

import com.nicoe.library.model.entities.Book;
import com.nicoe.library.model.entities.Copy;

import java.util.List;

public interface BookService {
    List<Book> bookList();
    Integer findAvailableBook(Integer id);
    List<Copy> loanInLate();
}
