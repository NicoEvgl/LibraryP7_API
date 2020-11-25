package com.nicoe.library.model.dao;

import com.nicoe.library.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByPseudo(String pseudo);
}
