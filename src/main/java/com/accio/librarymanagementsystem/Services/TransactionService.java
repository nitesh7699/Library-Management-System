package com.accio.librarymanagementsystem.Services;

import com.accio.librarymanagementsystem.Entity.Book;
import com.accio.librarymanagementsystem.Entity.LibraryCard;
import com.accio.librarymanagementsystem.Entity.Transaction;
import com.accio.librarymanagementsystem.Enums.TransactionStatus;
import com.accio.librarymanagementsystem.Repositories.BookRepository;
import com.accio.librarymanagementsystem.Repositories.CardRepository;
import com.accio.librarymanagementsystem.Repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public static final Integer MAX_NO_OF_ISSUED_BOOKS = 3;

    public static final Integer FINE_PER_DAY = 5;

    public String issueBook(Integer bookId, Integer cardId) throws Exception{

        //Find the book and the card from the repositories
        //Create the transaction object
        //set the relevant attributes of the transactionObject
        //change the attributes of card and book entity

        //1. Book and LibraryCard should be valid -->
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new Exception("BookId entered is incorrect");
        }
        Book book = bookOptional.get(); //100% guarantee that book is there

        log.info("Book Object is "+ book.toString());

        //2. Library Card should be valid -->
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if(optionalLibraryCard.isEmpty()){
            log.error("cardId is incorrect");
            throw new Exception("CardId entered is incorrect");
        }
        LibraryCard card = optionalLibraryCard.get();

        log.info("The card object is "+ card.toString());

        //here we are starting the transaction --
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.PENDING); //ByDefault we are setting the transaction status as PENDING

        //3. book should not be already issued
        if(book.getIsIssued() == true){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            log.debug("Book is in issued status");
            return "Book is already issued to cardId " + card.getCardNo();
        }

        //4. Limit of the card has been exceeded
        if(card.getNoOfBooksIssued() > MAX_NO_OF_ISSUED_BOOKS){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Max limit of this card has been exceeded";
        }

        //5. Check for if the card has Expired its validity
        Long timeInMsOfCardValidity = card.getValidity().getTime();
        Long currentTimeInMs = System.currentTimeMillis();
        if(currentTimeInMs > timeInMsOfCardValidity){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "The card has been expired";
        }

        //HAPPY FLOW --
        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        book.setIsIssued(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued() + 1);

        //Rule is whatever has been modified should be SAVED!!!
        cardRepository.save(card);
        bookRepository.save(book);
        transaction = transactionRepository.save(transaction);

        return "The Transaction has been completed with transactionID " + transaction.getTransactionId();
    }

    public String returnBook(Integer bookId, Integer cardId){

        //I need to find out the transaction: with that bookId, cardId and ISSUED status

        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        Transaction transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book, card, TransactionStatus.ISSUED);

        //Fine amount to be calculated:
        Long timeDifferenceInMs = System.currentTimeMillis() - transaction.getIssueDate().getTime();

        //this time is in MS, we need to convert this to days
        Long days = TimeUnit.DAYS.convert(timeDifferenceInMs, TimeUnit.MILLISECONDS);

        Integer fineAmt = 0;
        if(days>15){
            fineAmt = Math.toIntExact((days-15) * FINE_PER_DAY);
        }

        //Modify the changes and SAVE it
        transaction.setFineAmount(fineAmt);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setReturnDate(new Date()); //It will automatically set the current Date in the system
        book.setIsIssued(Boolean.FALSE);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        transactionRepository.save(transaction);
        cardRepository.save(card);
        bookRepository.save(book);

        return "The book has been successfully returned!";


    }

}























































