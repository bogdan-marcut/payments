package com.bogdan.employee.service;

import javax.inject.Inject;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import com.bogdan.employee.model.Employee;
import com.bogdan.employee.repository.EmployeeRepository;

/**
 * @author bogdan.marcut 24/01/2021.
 */
@Controller("/employee")
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeService(final EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
    }

    @Get(value = "{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Employee> getEmployee(@PathVariable final Long id) {
	final Employee employee = this.employeeRepository.getEmployee(id);

	return HttpResponse.ok()
		.body(employee);
    }

    @Post(value = "", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Employee> addEmployee(@PathVariable final Long id) {
	final Employee employee = this.employeeRepository.getEmployee(id);

	return HttpResponse.ok()
		.body(employee);
    }

}
