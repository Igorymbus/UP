package com.example.demo.repository;

import com.example.demo.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> { }


