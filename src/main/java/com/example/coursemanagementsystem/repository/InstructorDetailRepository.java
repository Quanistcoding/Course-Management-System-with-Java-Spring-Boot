package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.entity.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

public interface InstructorDetailRepository extends JpaRepository<InstructorDetail,Integer> {
}
