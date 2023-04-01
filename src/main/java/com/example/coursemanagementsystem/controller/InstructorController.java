package com.example.coursemanagementsystem.controller;


import com.example.coursemanagementsystem.entity.Instructor;
import com.example.coursemanagementsystem.entity.InstructorDetail;
import com.example.coursemanagementsystem.repository.CourseRepository;
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
    private CourseRepository courseRepository;

    public InstructorController(InstructorRepository instructorRepository,CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
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

    @GetMapping("/{instructorId}")
    public String getDetailPage(@PathVariable int instructorId, Model model){

        var instructor = instructorRepository.getReferenceById(instructorId);

        if(instructor.getInstructorDetail() == null)
            instructor.setInstructorDetail(new InstructorDetail());

        model.addAttribute("instructor",instructor);

        return "instructors/detail";
    }

    @GetMapping("/{instructorId}/edit")
    public String getDetailEditPage(@PathVariable int instructorId, Model model){

        var instructor = instructorRepository.getReferenceById(instructorId);

        model.addAttribute("instructor",instructor);

        return "instructors/detailEdit";
    }

    @PostMapping("/{instructorId}/edit")
    public String HandleEditDetail(@Valid Instructor instructor,
                                   BindingResult result,
                                   Model model,
                                   @PathVariable int instructorId
    ){
        if(result.hasErrors())
            return "instructors/detailEdit";

        instructorRepository.save(instructor);

        model.addAttribute("instructor",instructor);

        return "redirect:/instructors/" + instructorId;
    }

    @GetMapping("{instructorId}/deselect/{courseId}")
    public String deselectCourse(@PathVariable int instructorId, @PathVariable int courseId){
        var course = courseRepository.getReferenceById(courseId);
        course.setInstructor(null);

        courseRepository.save(course);
        return "redirect:/instructors/" + instructorId;
    }
}
