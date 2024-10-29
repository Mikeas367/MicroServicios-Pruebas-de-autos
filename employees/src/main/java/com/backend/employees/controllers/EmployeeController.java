package com.backend.employees.controllers;

import com.backend.employees.exceptions.InvalidDataException;
import com.backend.employees.models.Employee;
import com.backend.employees.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return employeeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        employeeService.getById(id);
        employeeService.delete(id);
    }

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        employeeService.validateEmployee(employee);
        return employeeService.save(employee);
    }


}
