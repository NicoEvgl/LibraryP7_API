package com.nicoe.library.model.dao;

import com.nicoe.library.model.entities.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CopyDao extends JpaRepository<Copy, Integer> {
    List<Copy> findAllByUser_Id(Integer id);
    List<Copy> findAllByAvailableFalse();
    List<Copy> findAllByBook_IdAndAvailableTrue(Integer id);
    List<Copy> findAllByLoanEndDateBefore(Date loanEndDate);
    Copy findByCopyId(Integer id);
}
