package com.sg.springbootmonitoring.api;

import com.sg.springbootmonitoring.dto.EmployeeDto;
import com.sg.springbootmonitoring.service.EmployeeService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Counted(value = "create_employee_request_count", description = "Count of create employee request")
    @Timed(value = "create_employee_latency", description = "Time taken to create employee")
    @PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employee) {
        return employeeService.createEmployee(employee);
    }

    @Counted(value = "get_employee_request_count", description = "Count of get employee request")
    @Timed(value = "get_employee_latency", description = "Time taken to get employee")
    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") long id) {
        return employeeService.getEmployee(id);
    }

    @Counted(value = "get_all_employees_request_count", description = "Count of get all employees request")
    @Timed(value = "get_all_employees_latency", description = "Time taken to get all employees")
    @GetMapping("/employees")
    public List<EmployeeDto> getEmployees() {
        return employeeService.getAllEmployees();
    }


}
