
package com.hostel.controller;

import com.hostel.service.AttendanceService;
import com.hostel.service.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService service;
    public AttendanceController(AttendanceService service){ this.service = service; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("records", service.all());
        return "attendance";
    }

    @PostMapping("/mark")
    public String mark(@RequestParam Long studentId,
                       @RequestParam String date,
                       @RequestParam boolean present,
                       RedirectAttributes ra){
        try {
            service.mark(studentId, LocalDate.parse(date), present);
            ra.addFlashAttribute("message", "Attendance saved");
        } catch (NotFoundException e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/attendance";
    }
}
