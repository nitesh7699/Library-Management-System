package com.accio.librarymanagementsystem.Controllers;

import com.accio.librarymanagementsystem.Entity.Author;
import com.accio.librarymanagementsystem.Entity.Book;
import com.accio.librarymanagementsystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author){ //JSON -> Java object
        String result = authorService.addAuthor(author);
        return result;
    }

    @GetMapping("/getAuthorWithMaxBooks")
    public Author getAuthor(){
        Author ansAuthor = authorService.getAuthorWithMaxBooks();
        return ansAuthor;
    }

}











































































































