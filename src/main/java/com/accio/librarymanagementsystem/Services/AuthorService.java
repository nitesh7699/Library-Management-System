package com.accio.librarymanagementsystem.Services;


import com.accio.librarymanagementsystem.Entity.Author;
import com.accio.librarymanagementsystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author){
        author.setNoOfBooks(0); //setting the default value as 0 and not null
        authorRepository.save(author);
        return "Author has been saved to the DB";
    }

    public Author getAuthorWithMaxBooks(){
        List<Author> authorList = authorRepository.findAll();

        Author ansAuthor = null;
        int maxBooks=0;

        for(Author author : authorList){
            if(author.getNoOfBooks()> maxBooks){
                maxBooks = author.getNoOfBooks();
                ansAuthor = author;
            }
        }
        return ansAuthor;

    }


}
