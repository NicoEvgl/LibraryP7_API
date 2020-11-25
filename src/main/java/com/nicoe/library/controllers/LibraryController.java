package com.nicoe.library.controllers;

import com.nicoe.library.Services.BookService;
import com.nicoe.library.Services.LibraryService;
import com.nicoe.library.model.entities.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @Autowired
    BookService bookService;

    @GetMapping("/library")
    public List<Library> copiesList() {
        List<Library> libraryList = libraryService.libraryList();
        return libraryList;
    }
}
