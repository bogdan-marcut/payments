package com.bogdan.employee.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Handler;

import com.bogdan.ApiService;
import com.bogdan.employee.model.Employee;
import com.bogdan.employee.repository.EmployeeRepository;
import com.bogdan.employee.repository.EmployeeRepositoryProcessor;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class EmployeeService implements ApiService {

    private final EmployeeRepositoryProcessor employeeRepositoryProcessor;

    public EmployeeService(final EmployeeRepository employeeRepository) {
	this.employeeRepositoryProcessor = new EmployeeRepositoryProcessor(employeeRepository);
    }

    @Override
    public Action<Chain> buildServiceApi() {
	return apiChain -> apiChain
		.prefix("employees", this.employeesApi());
    }

    private Action<Chain> employeesApi() {
	return chain -> chain
		.get(":id", this.getEmployee())
		.post("", this.addEmployee());
    }

    private Handler getEmployee() {
	return ctx -> {
	    final String employeeId = ctx.getPathTokens().get("id");

	    final CompletableFuture<Employee> employee = this.employeeRepositoryProcessor.findById(Long.valueOf(employeeId));
	    this.renderResponse(ctx, employee);
	};
    }

    private Handler addEmployee() {
	return ctx -> {
	    ctx.parse(Employee.class)
		    .then(employee -> {
			final CompletionStage<Employee> newEmployee = this.employeeRepositoryProcessor.addEmployee(employee.id, employee.name);
			this.renderResponse(ctx, newEmployee);
		    });
	};
    }

}
