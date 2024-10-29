package com.backend.employees.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "employees")
public class Employee {

    // legajo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String firstName;
    private String lastName;
    private String telephoneNumber;

}
