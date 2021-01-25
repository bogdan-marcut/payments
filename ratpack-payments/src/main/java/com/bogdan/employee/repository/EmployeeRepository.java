package com.bogdan.employee.repository;

import com.bogdan.employee.model.Employee;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public interface EmployeeRepository {

    Employee findById(Long id);

    Employee addEmployee(Employee employee);

}
