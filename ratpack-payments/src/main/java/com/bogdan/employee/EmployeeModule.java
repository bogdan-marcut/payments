package com.bogdan.employee;

import com.bogdan.employee.repository.EmployeeRepository;
import com.bogdan.employee.repository.EmployeeRepositoryImpl;
import com.bogdan.employee.service.EmployeeChain;
import com.google.inject.AbstractModule;

/**
 * @author Bogdan Marcut 18/01/2021.
 */
public class EmployeeModule extends AbstractModule {

    @Override
    protected void configure() {
	this.bind(EmployeeRepository.class).to(EmployeeRepositoryImpl.class);
	this.bind(EmployeeChain.class);
    }

}
