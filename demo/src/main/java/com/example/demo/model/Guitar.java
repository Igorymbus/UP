package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Guitar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 150)
    @Column(nullable = false)
    private String model;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;

    @NotNull
    @Min(0)
    private Integer stock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Size(max = 255)
    private String color;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}


