package com.example.coursemanagementsystem.controller;


import com.example.coursemanagementsystem.entity.Instructor;
import com.example.coursemanagementsystem.repository.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("")
    public String getListPage(Model model){
        var instructors = instructorRepository.findAll();
        model.addAttribute("instructors",instructors);

        return "instructors/list";
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

        instructorRepository.save(instructor);

        return "redirect:/instructors";
    }

    @GetMapping("/{instructorId}/delete")
    public String handleDelete(@PathVariable int instructorId){

        instructorRepository.deleteById(instructorId);

        return "redirect:/instructors";
    }
}
