
package com.hostel.controller;

import com.hostel.model.Room;
import com.hostel.service.NotFoundException;
import com.hostel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;
    public RoomController(RoomService service){ this.service = service; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("rooms", service.all());
        return "rooms";
    }

    @PostMapping("/add")
    public String add(@RequestParam String roomNo, @RequestParam int capacity, RedirectAttributes ra){
        service.save(new Room(roomNo, capacity));
        ra.addFlashAttribute("message", "Room added");
        return "redirect:/rooms";
    }

    @PostMapping("/delete/{roomNo}")
    public String delete(@PathVariable String roomNo, RedirectAttributes ra){
        service.delete(roomNo);
        ra.addFlashAttribute("message", "Room deleted");
        return "redirect:/rooms";
    }

    @PostMapping("/allocate")
    public String allocate(@RequestParam String roomNo, @RequestParam Long studentId, RedirectAttributes ra){
        try {
            boolean ok = service.allocate(roomNo, studentId);
            if(!ok) ra.addFlashAttribute("error", "Room is full");
            else ra.addFlashAttribute("message", "Allocated");
        } catch (NotFoundException e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/rooms";
    }

    @PostMapping("/deallocate")
    public String deallocate(@RequestParam String roomNo, @RequestParam Long studentId, RedirectAttributes ra){
        try {
            service.deallocate(roomNo, studentId);
            ra.addFlashAttribute("message", "Deallocated");
        } catch (NotFoundException e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/rooms";
    }
}
