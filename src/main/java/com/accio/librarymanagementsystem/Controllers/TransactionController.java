package com.accio.librarymanagementsystem.Controllers;

import com.accio.librarymanagementsystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("issueBook")
    public String issueBook(@RequestParam("cardId") Integer cardId,
                            @RequestParam("bookId")Integer bookId){

        try{
            String  result = transactionService.issueBook(bookId, cardId);
            return result;
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/returnBook")
    public String returnBook(@RequestParam("bookId") Integer bookId,
                             @RequestParam("cardId") Integer cardId){


        String result = transactionService.returnBook(bookId, cardId);
        return result;
    }
}















