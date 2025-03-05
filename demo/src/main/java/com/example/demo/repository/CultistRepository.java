package com.example.demo.repository;

import com.example.demo.model.Cultist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultistRepository extends JpaRepository<Cultist, Long> {
}
