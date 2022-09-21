package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.RendezVous;

public interface RendezVousRepository extends JpaRepository <RendezVous, Long> {
    
}
