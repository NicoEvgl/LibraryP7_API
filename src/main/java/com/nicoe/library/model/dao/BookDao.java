package com.nicoe.library.model.dao;

import com.nicoe.library.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookDao extends JpaRepository<Book, Integer> {
    List<Book> findAll();
}
