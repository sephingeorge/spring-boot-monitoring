package com.sg.springbootmonitoring.service;

import com.sg.springbootmonitoring.dao.EmployeeJPARepository;
import com.sg.springbootmonitoring.dto.EmployeeDto;
import com.sg.springbootmonitoring.entity.Employee;
import com.sg.springbootmonitoring.util.UniqueCode;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeJPARepository repository;
    public EmployeeService(EmployeeJPARepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        var employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        employee.setCode(UniqueCode.appendUniqueCode());
        employee = repository.save(employee);
        employee.setId(employee.getId());
        return employeeDto;
    }

    @Cacheable("employees")
    public EmployeeDto getEmployee(long id) {
        var employee = repository.findById(id).orElseThrow();
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getSalary());
    }

    public List<EmployeeDto> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(employee -> new EmployeeDto(employee.getId(), employee.getName(), employee.getSalary()))
                .toList();
    }
}
