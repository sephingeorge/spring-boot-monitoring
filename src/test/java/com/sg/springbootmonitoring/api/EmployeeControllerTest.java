package com.sg.springbootmonitoring.api;

import com.sg.springbootmonitoring.dto.EmployeeDto;
import com.sg.springbootmonitoring.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({EmployeeController.class, EmployeeService.class})
class EmployeeControllerTest {

    private static final String BASE_URL = "/api/v1";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    void testCreateEmployee_ReturnNewEmployeeDto_WhenEmployeeInfoPassed() throws Exception {
        var mockEmployeeResponseDto = new EmployeeDto(1, "test_name", 1000.00);
        doReturn(mockEmployeeResponseDto).when(employeeService).createEmployee(any());
        mockMvc.perform(post(BASE_URL + "/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name" : "test_name",
                            "salary" : 1000.00
                        }
                        """)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockEmployeeResponseDto.getId()))
                .andExpect(jsonPath("$.name").value(mockEmployeeResponseDto.getName()))
                .andExpect(jsonPath("$.salary").value(mockEmployeeResponseDto.getSalary()));
    }

}
