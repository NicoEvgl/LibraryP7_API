package com.nicoe.library.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "library", schema = "public")
public class Library implements Serializable {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    @Column (name = "id",nullable = false)
    private Integer id;
    @Column (name = "location",nullable = false,length = 255)
    private String location;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private Set<Copy> copies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }
}
