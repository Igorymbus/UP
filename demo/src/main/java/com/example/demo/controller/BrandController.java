package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.repository.BrandRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        return "brands/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("brand", new Brand());
        return "brands/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("brand") Brand brand, BindingResult result) {
        if (result.hasErrors()) {
            return "brands/form";
        }
        brandRepository.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("brand", brandRepository.findById(id).orElseThrow());
        return "brands/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("brand") Brand brand, BindingResult result) {
        if (result.hasErrors()) {
            return "brands/form";
        }
        brand.setId(id);
        brandRepository.save(brand);
        return "redirect:/brands";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        brandRepository.deleteById(id);
        return "redirect:/brands";
    }
}


