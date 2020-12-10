package com.nicoe.library.Services;

import com.nicoe.library.Services.impl.BookFromCriteria;
import com.nicoe.library.model.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookCustomService {
    List<Book> multiCriteriaBook(BookFromCriteria criteria);
}
