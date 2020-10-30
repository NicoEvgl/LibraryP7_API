package com.nicoe.library.model.dao;

import com.nicoe.library.model.entities.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyDao extends JpaRepository<Copy, Integer> {
}
