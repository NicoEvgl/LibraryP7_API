package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.BookService;
import com.nicoe.library.model.dao.BookDao;
import com.nicoe.library.model.dao.CopyDao;
import com.nicoe.library.model.entities.Book;
import com.nicoe.library.model.entities.Copy;
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

    @Override
    public List<Book> bookList() {
        List<Book> books = bookDao.findAll();
        return books;
    }

    @Override
    public Integer findAvailableBook(Integer id) {
        Integer nbAvailable = copyDao.findAllByBook_IdAndAvailableTrue(id).size();
        return nbAvailable;
    }

    @Override
    public List<Copy> loanInLate() {
        Date date = new Date();
        List<Copy> copies = copyDao.findAllByLoanEndDateBefore(date);
        return copies;
    }
}
