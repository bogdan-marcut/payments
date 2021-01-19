package com.bogdan.employee;

import java.nio.file.Paths;
import java.util.concurrent.CompletionStage;

import com.bogdan.employee.model.Employee;
import com.bogdan.employee.repository.EmployeeRepositoryImpl;
import com.bogdan.employee.service.EmployeeService;
import com.bogdan.employee.repository.EmployeeRepository;

/**
 * @author Bogdan Marcut 18/01/2021.
 */
public class EmployeeModule {

    private final EmployeeRepository employeeRepository;


    public EmployeeModule(EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
    }

    public EmployeeModule() {
	this(new EmployeeRepositoryImpl());
    }

    public EmployeeService createService() {
	return new EmployeeService(this.employeeRepository);
    }

}
