package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "order_id", unique = true)
    private ShopOrder order;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double amount;

    @NotNull
    private LocalDateTime paidAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private PaymentMethod method = PaymentMethod.CARD;

    public enum PaymentMethod { CARD, CASH, TRANSFER }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ShopOrder getOrder() { return order; }
    public void setOrder(ShopOrder order) { this.order = order; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }
}


