package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private ShopOrder order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "guitar_id")
    private Guitar guitar;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private Double unitPrice;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ShopOrder getOrder() { return order; }
    public void setOrder(ShopOrder order) { this.order = order; }
    public Guitar getGuitar() { return guitar; }
    public void setGuitar(Guitar guitar) { this.guitar = guitar; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
}


