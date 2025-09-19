package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customers/form";
        }
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id).orElseThrow());
        return "customers/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customers/form";
        }
        customer.setId(id);
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }
}


