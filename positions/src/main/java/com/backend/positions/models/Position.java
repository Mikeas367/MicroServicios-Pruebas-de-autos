package com.backend.positions.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int vehicleId;

    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private int latitude;

    @Column(nullable = false)
    private int longitude;

    @PrePersist
    protected void onCreate() {
        fechaHora = LocalDateTime.now();
    }
}
