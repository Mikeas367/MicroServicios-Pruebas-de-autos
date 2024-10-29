package com.backend.employees.services;

import com.backend.employees.exceptions.InvalidDataException;
import com.backend.employees.exceptions.ResourceNotFoundExeption;
import com.backend.employees.models.Employee;
import com.backend.employees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void validateEmployee(Employee employee){
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
            throw new InvalidDataException("the first name is required");
        }
        if (employee.getLastName() == null || employee.getLastName().isEmpty()) {
            throw new InvalidDataException("the last name is required");
        }
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee getById(int id){
        return employeeRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundExeption("Employee not found"));
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void delete(int id){
        employeeRepository.deleteById(id);
    }


}
