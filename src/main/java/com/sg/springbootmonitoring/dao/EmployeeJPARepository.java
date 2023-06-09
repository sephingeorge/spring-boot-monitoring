package com.sg.springbootmonitoring.dao;

import com.sg.springbootmonitoring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJPARepository extends JpaRepository<Employee, Long> {
}
