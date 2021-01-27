package com.bogdan.employee.service;

import javax.inject.Inject;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import com.bogdan.employee.model.EmployeeDto;
import com.bogdan.employee.repository.EmployeeRepository;

/**
 * @author bogdan.marcut 24/01/2021.
 */
@Controller("/api/employee")
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeService(final EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
    }

    @Get(value = "{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<EmployeeDto> getEmployee(@PathVariable final Long id) {
	return this.employeeRepository.getEmployee(id)
		.map(employee -> HttpResponse.ok()
			.body(employee))
		.orElse(HttpResponse.badRequest());
    }

    @Post(value = "", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<EmployeeDto> addEmployee(@PathVariable final Long id) {
	return HttpResponse.ok();
    }

}
