package com.accio.librarymanagementsystem.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String name;

    private Integer age;

    private String emailId;

    private Double rating;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

    @Column(columnDefinition = "INT DEFAULT 0")
    private int noOfBooks;

    public Author(Integer authorId, int noOfBooks) {
        this.authorId = authorId;
        this.noOfBooks = noOfBooks;
    }

}






























