package com.backend.positions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TrialDTO {
    private LocalDateTime trialEndDate;
}
