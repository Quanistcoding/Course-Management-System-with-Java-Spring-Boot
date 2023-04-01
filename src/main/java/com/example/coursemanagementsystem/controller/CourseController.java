package com.example.coursemanagementsystem.controller;

import com.example.coursemanagementsystem.entity.Course;
import com.example.coursemanagementsystem.entity.Instructor;
import com.example.coursemanagementsystem.repository.CourseRepository;
import com.example.coursemanagementsystem.repository.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private InstructorRepository instructorRepository;
    private CourseRepository courseRepository;

    public CourseController(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("")
    public String getListPage(Model model){
        var courses = courseRepository.findAll();
        model.addAttribute("courses",courses);
        return "courses/list";
    }

    @GetMapping("/create")
    public String getCreatePage(@ModelAttribute Course course, Model model){
        var instructors =  instructorRepository.findAll();
        model.addAttribute("course",course);
        model.addAttribute("instructors",instructors);
        return "courses/create";
    }

    @PostMapping("/create")
    public String handleCreate(@Valid Course course, BindingResult result, Model model){
        System.out.println(course);
        if(result.hasErrors()) {
            var instructors =  instructorRepository.findAll();
            model.addAttribute("instructors",instructors);
            return "courses/create";
        }

        courseRepository.save(course);

        return "redirect:/courses";
    }
}
