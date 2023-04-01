package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.entity.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
