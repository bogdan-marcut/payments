package com.bogdan.employee.repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.bogdan.employee.model.Employee;

/**
 * @author Bogdan Marcut 19/01/2021.
 */
public class EmployeeRepositoryProcessor {

    private final EmployeeRepository employeeRepository;

    private final Executor writesExecutor = Executors.newSingleThreadExecutor();

    public EmployeeRepositoryProcessor(final EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
    }

    public CompletionStage<Employee> addEmployee(final Long id, final String name) {
	final CompletableFuture<Employee> result = new CompletableFuture<>();
	this.writesExecutor.execute(() -> result.complete(this.employeeRepository.addEmployee(id, name)));
	return result;
    }

    public CompletableFuture<Employee> findById(final Long id) {
	final CompletableFuture<Employee> result = new CompletableFuture<>();
	this.writesExecutor.execute(() -> result.complete(this.employeeRepository.findById(id)));
	return result;
    }

}
