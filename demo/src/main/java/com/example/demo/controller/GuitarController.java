package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.model.Guitar;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GuitarRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guitars")
public class GuitarController {
    private final GuitarRepository guitarRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public GuitarController(GuitarRepository guitarRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.guitarRepository = guitarRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("guitars", guitarRepository.findAll());
        return "guitars/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("guitar", new Guitar());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "guitars/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("guitar") Guitar guitar, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brands", brandRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "guitars/form";
        }
        guitarRepository.save(guitar);
        return "redirect:/guitars";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("guitar", guitarRepository.findById(id).orElseThrow());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "guitars/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("guitar") Guitar guitar, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brands", brandRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "guitars/form";
        }
        guitar.setId(id);
        guitarRepository.save(guitar);
        return "redirect:/guitars";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        guitarRepository.deleteById(id);
        return "redirect:/guitars";
    }
}


