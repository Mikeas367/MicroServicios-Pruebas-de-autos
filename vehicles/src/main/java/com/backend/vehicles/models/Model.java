package com.backend.vehicles.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;


    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
