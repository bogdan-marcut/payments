package com.bogdan.employee.repository;

import com.bogdan.employee.model.EmployeeDto;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public interface EmployeeRepository {

    EmployeeDto findById(Long id);

    Long addEmployee(EmployeeDto employeeDto);

}
