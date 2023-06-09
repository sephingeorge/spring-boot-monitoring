package com.sg.springbootmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDto {

    private long id;
    private String name;
    private double salary;
}
