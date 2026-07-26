package org.example.demospring.controller;

import org.example.demospring.model.Course;
import org.example.demospring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourse());

        return "index";

    }

    @RequestMapping(value="/HelloWorld")
    public String HelloWorld(Model model) {return "HelloWorld";}

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showNewCourseForm(@PathVariable (value = "id") long id, Model model) {
        Course course = courseService.getCourseById(id);

        model.addAttribute("course",course);
        return "update_course";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable (value = "id") long id ) {

        this.courseService.deleteCoursebyId(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 5;

        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField,sortDir);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses",listCourses);
        return "index";
    }
}

