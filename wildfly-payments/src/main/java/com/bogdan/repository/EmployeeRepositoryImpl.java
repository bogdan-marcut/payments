package com.bogdan.repository;

import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author Bogdan Marcut 19/01/2021.
 */
@Dependent
@Transactional(Transactional.TxType.REQUIRED)
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext(unitName = "pu-tol-db")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<EmployeeDto> findById(final Long id) {

	final EmployeeEntity value = this.entityManager.find(EmployeeEntity.class, id);
	return Optional.ofNullable(value)
		.map(employeeEntity -> EmployeeDto.builder()
			.id(employeeEntity.getId())
			.name(employeeEntity.getName())
			.title(employeeEntity.getTitle())
			.build());
    }

}
