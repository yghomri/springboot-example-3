package com.example.demo;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entities.*;
import com.example.demo.repositories.MedecinRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.RendezVousRepository;
import com.example.demo.service.IHospitalService;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
        IHospitalService hospitalService,
        PatientRepository patientRepository,
        MedecinRepository medecinRepository,
        RendezVousRepository rendezVousRepository
    ) {
        return args -> {
            // Create and save patients on DB 
            Stream.of("Deniro", "Alpacino", "Rocky").forEach(name -> {
                Patient patient = new Patient();
                patient.setName(name);
                patient.setBirthDate(new Date());
                patient.setSick(false);
                hospitalService.savePatient(patient);    
            });

            // Create and save physicians on DB
            Stream.of("Dodo", "Cino", "Kyo").forEach(name -> {
                Medecin medecin = new Medecin();
                medecin.setName(name);
                medecin.setSpeciality(Math.random() > 0.5 ? "Cardio" : "Dentist");
                hospitalService.saveMedecin(medecin);    
            });

            Patient patient1 = patientRepository.findByName("Alpacino");
            Medecin medecin1 = medecinRepository.findByName("Dodo");

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRdv.PENDING);
            rendezVous.setMedecin(medecin1);
            rendezVous.setPatient(patient1);
            hospitalService.saveRendezVous(rendezVous);

            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setReport("report for the consultation");
            hospitalService.saveConsultation(consultation);
        };
    }
}
