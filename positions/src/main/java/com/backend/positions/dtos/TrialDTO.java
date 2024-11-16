package com.backend.positions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrialDTO {
    private Long vehicleId;
    private Long interestedId;
    private Long employeeId;
    private LocalDateTime trialStartDate;
    private LocalDateTime trialEndDate;
    private String note;

}
