package com.bogdan.employee.repository;

import java.util.HashMap;
import java.util.Map;

import com.bogdan.employee.model.Employee;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Map<Long, Employee> employees;

    public EmployeeRepositoryImpl() {
	this.employees = new HashMap<>();
	this.employees.put(1L, new Employee(1L, "Mr", "John Doe"));
	this.employees.put(2L, new Employee(2L, "Mr", "White Snow"));
    }

    @Override
    public Employee findById(final Long id) {
	return this.employees.get(id);
    }

    @Override
    public Employee addEmployee(final Long id, final String name) {
	final var newEmployee = new Employee(id, name, "nameIncognito");
	this.employees.put(id, newEmployee);
	return newEmployee;
    }

}
