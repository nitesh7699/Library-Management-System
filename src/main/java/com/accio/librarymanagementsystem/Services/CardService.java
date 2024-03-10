package com.accio.librarymanagementsystem.Services;

import com.accio.librarymanagementsystem.Enums.CardStatus;
import com.accio.librarymanagementsystem.Entity.LibraryCard;
import com.accio.librarymanagementsystem.Entity.Student;
import com.accio.librarymanagementsystem.Repositories.CardRepository;
import com.accio.librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String generateCard(){

        //I want to save some Cards to the DB
        LibraryCard card = new LibraryCard(); //This object will go to the DB and save it
        card.setCardStatus(CardStatus.NEW);
        card.setNoOfBooksIssued(0);

        Date expiryDate = new Date(128, 6, 1); //date is there in the Java util package
        card.setValidity(expiryDate);

        card = cardRepository.save(card);

        return "The card has been generated " + card.getCardNo();

    }

    public String associateCardAndStudent(Integer cardID, Integer studentID){

        LibraryCard libraryCard = cardRepository.findById(cardID).get();
        Student student = studentRepository.findById(studentID).get();

        libraryCard.setCardStatus(CardStatus.ISSUED);
        libraryCard.setStudent(student); //Indirectly setting thr FK in the LC table
        //but bcz we are playing with entities so we will have to set as per the entity

        cardRepository.save(libraryCard); //save function works like hmap.put()

        return "The card and the student has been associated";
    }


}






































