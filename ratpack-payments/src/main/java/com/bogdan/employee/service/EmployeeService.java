package com.bogdan.employee.service;



import io.vavr.control.Option;
import ratpack.exec.Promise;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;

import com.bogdan.JsonMapping;
import com.bogdan.employee.model.Employee;
import com.bogdan.employee.repository.EmployeeRepository;
import com.bogdan.employee.repository.EmployeeRepositoryProcessor;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class EmployeeService {

    private final EmployeeRepositoryProcessor employeeRepositoryProcessor;

    public EmployeeService(EmployeeRepository employeeRepository) {
	this.employeeRepositoryProcessor = new EmployeeRepositoryProcessor(employeeRepository);
    }

    public Action<Chain> employeeApi() {
	return apiChain -> apiChain
		.prefix("employees", employees());

    }

    private Action<Chain> employees() {
	return chain -> chain
		.get(":id", getEmployee())
		.post("", addEmployee());

    }

    private Handler getEmployee() {
	return ctx -> {
	    final String employeeId = ctx.getPathTokens().get("id");

	    ctx.render(JsonMapping.toJsonPromise(employeeRepositoryProcessor.findById(Long.valueOf(employeeId))));
	};
    }

    private Handler addEmployee() {
	return ctx -> {
	    ctx.parse(Employee.class).then(
		    newUser -> ctx.render(JsonMapping.toJsonPromise(employeeRepositoryProcessor.addEmployee(newUser.id, newUser.name))));
	};
    }



}
