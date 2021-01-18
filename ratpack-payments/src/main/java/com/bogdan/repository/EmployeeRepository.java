package com.bogdan.repository;

import ratpack.exec.Promise;

import com.bogdan.model.Employee;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public interface EmployeeRepository {

    Promise<Employee> findById(Long id);
}
