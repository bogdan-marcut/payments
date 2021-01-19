package com.bogdan.employee.repository;

import java.util.HashMap;
import java.util.Map;

import com.bogdan.employee.model.Employee;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final Map<Long, Employee> employees = new HashMap<>();

    public EmployeeRepositoryImpl() {
	employees.put(1L, new Employee(1L, "Mr", "John Doe"));
	employees.put(2L, new Employee(2L, "Mr", "White Snow"));
    }

    @Override
    public Employee findById(final Long id) {
	return employees.get(id);
    }

    @Override
    public Employee addEmployee(Long id, String name) {
	final Employee newEmployee = new Employee(id, name, "Default Title");
	employees.put(id, newEmployee);
	return newEmployee;
    }

}
