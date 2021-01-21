package com.bogdan.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

/**
 * @author Bogdan Marcut 19/01/2021.
 */
@Dependent
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Map<Long, Employee> employees = new HashMap<>();

    @PostConstruct
    public void init(){
        this.employees.put(1L, Employee.builder().id(1L).build());
        this.employees.put(2L, Employee.builder().id(2L).build());
    }

    @Override
    public Optional<Employee> findById(final Long id){
        return Optional.ofNullable(employees.get(id));
    }

}
