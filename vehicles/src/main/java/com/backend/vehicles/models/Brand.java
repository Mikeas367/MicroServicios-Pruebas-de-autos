package com.backend.vehicles.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String brandName;
}
