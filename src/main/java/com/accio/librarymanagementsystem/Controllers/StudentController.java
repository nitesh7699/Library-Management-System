package com.accio.librarymanagementsystem.Controllers;


import com.accio.librarymanagementsystem.Entity.Student;
import com.accio.librarymanagementsystem.Services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //controller layer
@RequestMapping("/student") //helps us give a prefix in API
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        String result = studentService.addStudent(student);
        return result;
    }

    @GetMapping("/getTopperStudent")
    public List<Student> getStudents(@RequestParam("branch") String branch,
                                     @RequestParam("cgpa") double cgpa){

        List<Student> anslist = studentService.findStudent(branch, cgpa);
        return anslist;

    }

    @GetMapping("printLogs")
    public void printLogs(){
        log.trace("Hi this is a trace log");
        log.debug("Hi this is a debug log");
        log.info("Hi this is a info log");
        log.warn("Hi this is a warning log");
        log.error("Hi this is a error log");

    }

}

