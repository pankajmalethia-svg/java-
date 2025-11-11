
package com.hostel.controller;

import com.hostel.model.User;
import com.hostel.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserRepository users;
    public AuthController(UserRepository users){ this.users = users; }

    @GetMapping("/login")
    public String loginPage(){ return "login"; }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          RedirectAttributes ra){
        return users.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> { session.setAttribute("user", u); return "redirect:/"; })
                .orElseGet(() -> { ra.addFlashAttribute("error", "Invalid credentials"); return "redirect:/login"; });
    }

    @GetMapping("/signup")
    public String signupPage(){ return "signup"; }

    @PostMapping("/doSignup")
    public String doSignup(@RequestParam String username,
                           @RequestParam String password,
                           RedirectAttributes ra){
        if(users.existsByUsername(username)){
            ra.addFlashAttribute("error", "Username already exists");
            return "redirect:/signup";
        }
        users.save(new User(username, password));
        ra.addFlashAttribute("message", "Account created. Please login.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
