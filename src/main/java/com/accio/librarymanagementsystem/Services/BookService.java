package com.accio.librarymanagementsystem.Services;


import com.accio.librarymanagementsystem.Controllers.BookController;
import com.accio.librarymanagementsystem.Entity.Author;
import com.accio.librarymanagementsystem.Entity.Book;
import com.accio.librarymanagementsystem.Exceptions.InvalidPageCountException;
import com.accio.librarymanagementsystem.Repositories.AuthorRepository;
import com.accio.librarymanagementsystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book) throws Exception{
        if(book.getNoOfPages() <= 0){
            throw new InvalidPageCountException("Page Count entered is incorrect");
        }
        book.setIsIssued(Boolean.FALSE); //we need to set isIssued=false (byDefault)
        bookRepository.save(book);
        return "Book as been saved to the DB";
    }

    public String associateBookAndAuthor(Integer bookId, Integer authorId) throws Exception{

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            //Throw an exception that book is not found
            throw new Exception("BookId entered is incorrect");
        }
        Book book = bookOptional.get();
       //or, Book book = bookRepository.findById(bookId).get();

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()){
            //throw an exception saying authorId entered is not valid
            throw new Exception("AuthorId entered is incorrect");
        }
        Author author = optionalAuthor.get();
        //or, Author author = authorRepository.findById(authorId).get();

        //associating book and author entity
        book.setAuthor(author);
        author.setNoOfBooks(author.getNoOfBooks()+1);

        //Rule is whatever has been modified should be SAVED!!!
        bookRepository.save(book);
        authorRepository.save(author);

        return "Book and author have been associated";
    }

    public List<Book> findBookByAuthor(Integer authorId){

        List<Book> allBooks = bookRepository.findAll();

        //I need to iterate where:
        //book.getAuthor.getId is matching
        List<Book> ansList = new ArrayList<>();
        for(Book book: allBooks){
            if(book.getAuthor().getAuthorId().equals(authorId)){
                ansList.add(book);
            }
        }

        return ansList;
    }


}





















