package com.example.coursemanagementsystem.controller;

import com.example.coursemanagementsystem.entity.Instructor;
import com.example.coursemanagementsystem.entity.Review;
import com.example.coursemanagementsystem.repository.CourseRepository;
import com.example.coursemanagementsystem.repository.ReviewRepository;
import com.example.coursemanagementsystem.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewRepository reviewRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public ReviewController(ReviewRepository reviewRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.reviewRepository = reviewRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("")
    public String getListPage(Model model){
        var reviews = reviewRepository.findAll();
        model.addAttribute("reviews",reviews);

        return "reviews/list";
    }

    @GetMapping("/create")
    public String getCreatePage(@ModelAttribute Review review, Model model){
        var courses = courseRepository.findAll();
        var students = studentRepository.findAll();

        model.addAttribute("review",review);
        model.addAttribute("courses",courses);
        model.addAttribute("students",students);

        return "reviews/create";
    }

    @PostMapping("/create")
    public String handleCreate(@Valid Review review, BindingResult result, Model model){
        if(result.hasErrors()) {
            var courses = courseRepository.findAll();
            var students = studentRepository.findAll();

            model.addAttribute("courses",courses);
            model.addAttribute("students",students);

            return "reviews/create";
        }
        reviewRepository.save(review);

        return "redirect:/reviews";
    }
}
