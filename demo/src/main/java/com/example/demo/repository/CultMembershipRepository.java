package com.example.demo.repository;

import com.example.demo.model.CultMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultMembershipRepository extends JpaRepository<CultMembership, Long> {
}
