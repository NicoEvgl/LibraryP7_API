package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.LibraryService;
import com.nicoe.library.model.dao.LibraryDao;
import com.nicoe.library.model.entities.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    LibraryDao libraryDao;

    @Override
    public List<Library> libraryList() {
        List<Library> libraries = libraryDao.findAll();
        return libraries;
    }
}
