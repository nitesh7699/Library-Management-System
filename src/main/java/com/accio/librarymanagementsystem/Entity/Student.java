package com.accio.librarymanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity //this is the schema of how student table will look like
@Table(name = "student_info") //this also required
@Getter //lombok dependency handles all the getters
@Setter //lombok dependency handles all the setters
@NoArgsConstructor //This is like a default constructor (@Entity needs it)
@AllArgsConstructor //This is like a constructor having all the arguments
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo; //now this attribute will act as a PK after we have added @Id, it should be auto-generated

    @Column(nullable = false, unique = false)
    private String name;

    private String branch;

    @Column(nullable = false) //or, @Column(name = "studentInfo", nullable= false, unique = true) //by default name of the column is same as the variable name
    private double cgpa;

    @Column(unique = true)
    private String emailId;

    /*
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    */

}







































































