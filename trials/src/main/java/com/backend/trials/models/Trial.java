package com.backend.trials.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "trial")
public class Trial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int vehicleId;
    private int interestedId;
    private int employeeId;

    private LocalDateTime trialStartDate;
    private LocalDateTime trialEndDate;

    private String note;

}
