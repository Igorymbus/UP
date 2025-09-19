package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AppUserRepository users;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") AppUser user, BindingResult result, Model model) {
        if (users.existsByUsername(user.getUsername())) {
            result.rejectValue("username", "exists", "Имя пользователя уже занято");
        }
        if (users.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "exists", "Email уже используется");
        }
        if (result.hasErrors()) {
            return "auth/register";
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setRoles(Set.of("CUSTOMER"));
        users.save(user);
        return "redirect:/auth/login";
    }
}


