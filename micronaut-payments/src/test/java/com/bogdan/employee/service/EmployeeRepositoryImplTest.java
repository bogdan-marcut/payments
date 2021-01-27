package com.bogdan.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import io.micronaut.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bogdan.employee.model.EmployeeDto;
import com.bogdan.employee.repository.EmployeeRepository;

/**
 * @author bogdan.marcut 27/01/2021.
 */
class EmployeeRepositoryImplTest {

    private EmployeeService employeeService;

    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
	this.employeeRepository = Mockito.mock(EmployeeRepository.class);
	this.employeeService = new EmployeeService(this.employeeRepository);
    }

    @Test
    void should_get_an_employee() {
	final EmployeeDto expectedEmployee = EmployeeDto.builder()
		.id(1L)
		.name("Mock Name")
		.title("Mr")
		.build();
	given(this.employeeRepository.getEmployee(1L)).willReturn(Optional.of(expectedEmployee));

	final HttpResponse<EmployeeDto> actualEmployee = this.employeeService.getEmployee(1L);

	assertEquals(expectedEmployee, actualEmployee.body());
    }

}