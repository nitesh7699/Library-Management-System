package com.accio.librarymanagementsystem.Repositories;


import com.accio.librarymanagementsystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //repository layer
public interface StudentRepository extends JpaRepository<Student, Integer> { //it will save to the student table

        //Methods of interfaces are by default public static final

        //Here, Jpa will internally create the sql-query and execute it
        List<Student> findStudentByBranchAndCgpaGreaterThan(String branch, double cgpa);
        Student findStudentByEmailId(String emailId);


}





























