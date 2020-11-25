package com.nicoe.library.Services.impl;

import com.nicoe.library.Services.BookCustomService;
import com.nicoe.library.model.entities.Book;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookCustomServiceImpl implements BookCustomService {

    private EntityManager entityManager;

    public BookCustomServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> multiCriteriaBook(BookFromCriteria criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!criteria.getTitle().equals("")) {
            predicates.add(criteriaBuilder.equal(bookRoot. get("title"), criteria.getTitle()));
        }
        if (!criteria.getAuthor().equals("")) {
            predicates.add(criteriaBuilder.equal(bookRoot. get("author"), criteria.getAuthor()));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery.distinct(true)).getResultList();
    }
}
