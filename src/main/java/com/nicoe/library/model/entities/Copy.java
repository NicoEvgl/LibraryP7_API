package com.nicoe.library.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name = "copy", schema = "public")
public class Copy implements Serializable {
    @Id
    @GeneratedValue
    @Column (name = "id", nullable = false)
    private Integer id;
    @Column (name = "loan_start_date", nullable = true)
    private Date loanStarDate;
    @Column (name = "loan_end_date", nullable = true)
    private Date loanEndDate;
    @Column (name = "extend", nullable = false)
    private Boolean extend;
    @Column (name = "available", nullable = false)
    private Boolean isAvailable;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    //@JsonIgnore
    @JsonIgnoreProperties({"copies"})
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "library_id")
    private Library library;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLoanStarDate() {
        return loanStarDate;
    }

    public void setLoanStarDate(Date loanStarDate) {
        this.loanStarDate = loanStarDate;
    }

    public Date getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(Date loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public Boolean getExtend() {
        return extend;
    }

    public void setExtend(Boolean extend) {
        this.extend = extend;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
