package com.nicoe.library.model.dao;

import com.nicoe.library.model.entities.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CopyDao extends JpaRepository<Copy, Integer> {
    List<Copy> findAllByUser_UserId(Integer userId);
    List<Copy> findAllByAvailableFalse();
    List<Copy> findAllByBook_BookIdAndAvailableTrue(Integer bookId);
    List<Copy> findAllByLoanEndDateBefore(Date loanEndDate);
    Copy findByCopyId(Integer copyId);
    List<Copy> findAllByBook_BookId(Integer bookId);
    List<Copy> findAllByBook_BookIdOrderByLoanEndDate(Integer bookId);
}
