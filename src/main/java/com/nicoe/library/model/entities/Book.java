package com.nicoe.library.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book", schema = "public")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "book_id", nullable = false)
    private Integer bookId;
    @Column (name = "title", nullable = false, length = 255)
    private String title;
    @Column (name = "author", nullable = false, length = 255)
    private String author;

    @OneToMany (mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Copy> copies;

    @OneToMany (mappedBy = "book", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
