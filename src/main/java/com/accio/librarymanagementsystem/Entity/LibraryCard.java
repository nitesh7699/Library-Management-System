package com.accio.librarymanagementsystem.Entity;

import java.time.LocalDate;
import java.util.*;
import com.accio.librarymanagementsystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "card_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus; //of type enum [ISSUED, NEW, BLOCKED] , it is not a primitive datatype

    private int noOfBooksIssued;

    private Date Validity;

    @JoinColumn //This tells that a new column is added to the LB (join a new column)
    @OneToOne //it is a mapping
    private Student student; //joined with student table (here we have written inside the child table, which is LibraryCard Table)

}
























































