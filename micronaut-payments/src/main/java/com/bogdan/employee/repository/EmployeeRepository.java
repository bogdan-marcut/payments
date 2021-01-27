package com.bogdan.employee.repository;

import java.util.Optional;

import com.bogdan.employee.model.EmployeeDto;

/**
 * @author bogdan.marcut 24/01/2021.
 */
public interface EmployeeRepository {

    Optional<EmployeeDto> getEmployee(Long id);
}
