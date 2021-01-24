package com.bogdan.employee.repository;

import com.bogdan.employee.model.Employee;

/**
 * @author bogdan.marcut 24/01/2021.
 */
public interface EmployeeRepository {

    Employee getEmployee(Long id);
}
