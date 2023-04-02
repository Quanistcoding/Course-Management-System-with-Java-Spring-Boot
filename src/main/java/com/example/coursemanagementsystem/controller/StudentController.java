package com.example.coursemanagementsystem.controller;

import com.example.coursemanagementsystem.entity.Instructor;
import com.example.coursemanagementsystem.entity.Student;
import com.example.coursemanagementsystem.repository.CourseRepository;
import com.example.coursemanagementsystem.repository.InstructorRepository;
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
@RequestMapping("/students")
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("")
    public String getListPage(Model model){
        var students = studentRepository.findAll();
        model.addAttribute("students",students);

        return "students/list";
    }

    @GetMapping("/create")
    public String getCreatePage(@ModelAttribute Student student, Model model){
        model.addAttribute("student",student);
        return "students/create";
    }

    @PostMapping("/create")
    public String handleCreate(@Valid Student student, BindingResult result, Model model){
        if(result.hasErrors())
            return "students/create";

        System.out.println(student);
        studentRepository.save(student);

        return "redirect:/students";
    }
}
