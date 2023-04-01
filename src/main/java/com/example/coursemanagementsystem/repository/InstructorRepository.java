package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
}
