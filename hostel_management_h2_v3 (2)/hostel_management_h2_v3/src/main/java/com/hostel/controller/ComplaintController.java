
package com.hostel.controller;

import com.hostel.model.Complaint;
import com.hostel.service.ComplaintService;
import com.hostel.service.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService service;
    public ComplaintController(ComplaintService service){ this.service = service; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("complaints", service.all());
        model.addAttribute("complaint", new Complaint());
        return "complaints";
    }

    @PostMapping
    public String add(@ModelAttribute Complaint complaint, RedirectAttributes ra){
        try {
            service.saveOrUpdate(complaint);
            ra.addFlashAttribute("message", "Complaint saved");
        } catch (NotFoundException e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/complaints";
    }
}
