package com.backend.vehicles.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false, unique = true)
    private String plate;


    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
