package com.bogdan.employee.repository;

import java.util.HashMap;
import java.util.Map;

import io.micronaut.runtime.context.scope.ThreadLocal;

import com.bogdan.employee.model.Employee;

/**
 * @author bogdan.marcut 24/01/2021.
 */
@ThreadLocal
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Map<Long, Employee> employeeMap = new HashMap<>();

    public EmployeeRepositoryImpl() {
	this.employeeMap = new HashMap<>();
	this.employeeMap.put(1L, new Employee(1L, "Name", "Title"));
	this.employeeMap.put(2L, new Employee(2L, "Joe", "Title2"));
    }

    @Override
    public Employee getEmployee(final Long id) {
	return this.employeeMap.get(id);
    }

}
