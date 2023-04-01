package com.example.coursemanagementsystem.controller;

import com.example.coursemanagementsystem.entity.Instructor;
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

    @GetMapping("")
    public String getListPage(Model model){

        return "courses/list";
    }

    @GetMapping("/create")
    public String getCreatePage(@ModelAttribute Instructor instructor, Model model){
        model.addAttribute("instructor",instructor);
        return "instructors/create";
    }

    @PostMapping("/create")
    public String handleCreate(@Valid Instructor instructor, BindingResult result, Model model){
        if(result.hasErrors())
            return "instructors/create";


        return "redirect:/instructors";
    }
}
