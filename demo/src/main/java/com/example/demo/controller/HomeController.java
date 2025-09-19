package com.example.demo.controller;

import com.example.demo.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final GuitarRepository guitarRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public HomeController(GuitarRepository guitarRepository, BrandRepository brandRepository, 
                         CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.guitarRepository = guitarRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("guitarCount", guitarRepository.count());
        model.addAttribute("brandCount", brandRepository.count());
        model.addAttribute("categoryCount", categoryRepository.count());
        model.addAttribute("customerCount", customerRepository.count());
        return "home";
    }

    @GetMapping("/calc")
    String calc() {
        return "calc";
    }
}
