package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByName(String name);
}
