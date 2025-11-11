
package com.hostel.controller;

import com.hostel.service.FeeService;
import com.hostel.service.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/fees")
public class FeeController {

    private final FeeService service;
    public FeeController(FeeService service){ this.service = service; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("payments", service.all());
        return "fees";
    }

    @PostMapping("/add")
    public String add(@RequestParam Long studentId,
                      @RequestParam Double amount,
                      @RequestParam(defaultValue = "PAID") String status,
                      RedirectAttributes ra){
        try {
            service.addOrUpdate(studentId, amount, status);
            ra.addFlashAttribute("message", "Fee updated for student " + studentId);
        } catch (NotFoundException e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/fees";
    }
}
