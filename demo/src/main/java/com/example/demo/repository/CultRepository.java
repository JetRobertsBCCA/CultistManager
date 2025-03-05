package com.example.demo.repository;

import com.example.demo.model.Cult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultRepository extends JpaRepository<Cult, Long> {
}
