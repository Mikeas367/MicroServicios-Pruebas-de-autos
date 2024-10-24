package com.backend.positions.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int vehicleId;


    @Column(nullable = false)
    private int latitude;

    @Column(nullable = false)
    private int longitude;
}
