package com.accio.librarymanagementsystem.Entity;


import com.accio.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(unique = true)
    private String title;

    private Integer noOfPages;

    private Integer price;

    private Boolean isIssued;

    @Enumerated(value = EnumType.STRING)
    private Genre genre; //enum type

    //the FK is always written in the Child class
    @JoinColumn
    @ManyToOne //Many books -> One author (Logic -> @CurrentClass To OtherClass)
    private Author author;
}



















