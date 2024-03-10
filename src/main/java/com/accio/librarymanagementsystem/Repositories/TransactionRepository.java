package com.accio.librarymanagementsystem.Repositories;

import com.accio.librarymanagementsystem.Entity.Book;
import com.accio.librarymanagementsystem.Entity.LibraryCard;
import com.accio.librarymanagementsystem.Entity.Transaction;
import com.accio.librarymanagementsystem.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
