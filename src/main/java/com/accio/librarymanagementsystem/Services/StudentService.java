package com.accio.librarymanagementsystem.Services;


import com.accio.librarymanagementsystem.Entity.Student;
import com.accio.librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //service layer
public class StudentService {

    @Autowired
    private StudentRepository studentRepository; //makes a new object from IOC

    public String addStudent(Student student){
        studentRepository.save(student);
        return "Student has been saved to the DB";
    }

    public List<Student> findStudent(String branch, double cgpa){

        List<Student> students = studentRepository.findStudentByBranchAndCgpaGreaterThan(branch, cgpa);
        return students;
    }

}

