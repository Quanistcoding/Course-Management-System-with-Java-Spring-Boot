package com.example.coursemanagementsystem.entity;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String StudentId;
    private String lastName;
    private String firstName;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
