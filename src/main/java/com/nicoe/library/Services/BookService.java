package com.nicoe.library.Services;

import com.nicoe.library.model.entities.Book;
import com.nicoe.library.model.entities.Copy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> bookList();
    Integer findAvailableBook(Integer id);
    List<Copy> loanInLate();
}
