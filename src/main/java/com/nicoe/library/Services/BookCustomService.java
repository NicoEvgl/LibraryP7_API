package com.nicoe.library.Services;

import com.nicoe.library.Services.impl.BookFromCriteria;
import com.nicoe.library.model.entities.Book;

import java.util.List;

public interface BookCustomService {
    List<Book> multiCriteriaBook(BookFromCriteria criteria);
}
