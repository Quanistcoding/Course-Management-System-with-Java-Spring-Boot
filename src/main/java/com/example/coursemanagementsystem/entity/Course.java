package com.example.coursemanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String courseId;
    @NotBlank
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Course() {
    }

    public Course(String courseId, String title, String description) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}


