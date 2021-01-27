package com.bogdan.service;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bogdan.repository.EmployeeDto;
import com.bogdan.repository.EmployeeRepository;

/**
 * @author Bogdan Marcut 19/01/2021.
 */
@RequestScoped
@Path("/employee")
public class EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") final Long id) {
	final Optional<EmployeeDto> optionalEmployee = this.employeeRepository.findById(id);

	return optionalEmployee.map(employeeDto -> Response.ok(employeeDto).build())
		.orElse(Response.serverError().build());
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(final EmployeeDto employee) {
	final Optional<Integer> optionalEmployee = this.employeeRepository.addEmployee(employee);

	return optionalEmployee.map(employeeDto -> Response.ok(employeeDto).build())
		.orElse(Response.serverError().build());
    }

}
