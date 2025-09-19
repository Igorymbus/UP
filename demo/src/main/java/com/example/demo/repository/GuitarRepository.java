package com.example.demo.repository;

import com.example.demo.model.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuitarRepository extends JpaRepository<Guitar, Long> { }


