package com.accio.librarymanagementsystem.Controllers;


import com.accio.librarymanagementsystem.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/generateCard")
    public ResponseEntity addCard(){
        String result = cardService.generateCard();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam("cardID") Integer cardID,
                                                  @RequestParam("studentID")Integer studentID){

        String res = cardService.associateCardAndStudent(cardID, studentID);
        return new ResponseEntity(res, HttpStatus.OK);
    }

}



































