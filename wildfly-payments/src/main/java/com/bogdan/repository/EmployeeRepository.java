package com.bogdan.repository;

import java.util.Optional;

/**
 * @author Bogdan Marcut 19/01/2021.
 */
public interface EmployeeRepository {

    Optional<EmployeeDto> findById(Long id);
}
