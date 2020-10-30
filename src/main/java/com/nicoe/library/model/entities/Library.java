package com.nicoe.library.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "library", schema = "public")
public class Library implements Serializable {
}
