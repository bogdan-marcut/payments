package com.bogdan.employee.repository;

import java.util.HashMap;
import java.util.Map;

import ratpack.http.ClientErrorException;

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
    public Employee addEmployee(final Employee employee) {
	if (employee == null || employee.getId() == null) {
	    throw new ClientErrorException("Id is not informed") {
		@Override
		public int getClientErrorCode() {
		    return 400;
		}
	    };
	}
	this.employees.put(employee.getId(), employee);
	return employee;
    }

}
