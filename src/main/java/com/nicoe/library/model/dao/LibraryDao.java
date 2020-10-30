package com.nicoe.library.model.dao;

import com.nicoe.library.model.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryDao extends JpaRepository<Library, Integer> {
}
