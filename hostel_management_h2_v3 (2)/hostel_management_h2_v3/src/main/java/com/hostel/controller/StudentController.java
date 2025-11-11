
package com.hostel.controller;

import com.hostel.model.Student;
import com.hostel.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    public StudentController(StudentService service){ this.service = service; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("students", service.all());
        return "students";
    }

    @PostMapping("/add")
    public String add(@RequestParam Long id,
                      @RequestParam String name,
                      @RequestParam String email,
                      @RequestParam String phone,
                      RedirectAttributes ra){
        service.save(new Student(id, name, email, phone));
        ra.addFlashAttribute("message", "Student saved");
        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra){
        if(!service.exists(id)){
            ra.addFlashAttribute("error", "No such student with ID " + id);
        } else {
            service.delete(id);
            ra.addFlashAttribute("message", "Student deleted");
        }
        return "redirect:/students";
    }
}
