package com.bogdan.employee.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.micronaut.runtime.context.scope.ThreadLocal;
import io.micronaut.transaction.annotation.ReadOnly;

import com.bogdan.employee.model.EmployeeDto;

/**
 * @author bogdan.marcut 24/01/2021.
 */
@ThreadLocal
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public EmployeeRepositoryImpl(final EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    @ReadOnly
    @Override
    public Optional<EmployeeDto> getEmployee(final Long id) {
	return Optional.ofNullable(this.entityManager.find(EmployeeEntity.class, id))
		.map(employeeEntity -> EmployeeDto.builder()
			.id(employeeEntity.getId())
			.name(employeeEntity.getName())
			.title(employeeEntity.getTitle())
			.build());
    }

}
