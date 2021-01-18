package com.bogdan.repository;

import java.util.HashMap;
import java.util.Map;

import ratpack.exec.Promise;

import com.bogdan.model.Employee;

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
    public Promise<Employee> findById(final Long id) {
	return Promise.async(downstream -> {
	    Thread.sleep(500);
	    downstream.success(employees.get(id));
	});
    }

}
