package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    
}
