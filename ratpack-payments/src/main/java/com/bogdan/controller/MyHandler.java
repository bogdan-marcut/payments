package com.bogdan.controller;

import java.net.URI;

import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.client.HttpClient;
import ratpack.http.client.ReceivedResponse;

import com.bogdan.model.Employee;
import com.bogdan.repository.EmployeeRepository;


/**
 * @author bogdan.marcut 18/01/2021.
 */
public class MyHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {

	HttpClient client = ctx.get(HttpClient.class);
	URI uri = URI.create("http://localhost:5050/employee/1");
	Promise<ReceivedResponse> responsePromise = client.get(uri);
	responsePromise.map(response -> response.getBody()
		.getText()
		.toUpperCase())
		.then(responseText -> ctx.getResponse()
			.send(responseText));


	final EmployeeRepository repository = ctx.get(EmployeeRepository.class);
	final Long id = Long.valueOf(ctx.getPathTokens().get("id"));
	final Promise<Employee> employeePromise = repository.findById(id);
	employeePromise.map(Employee::getName)
		.then(name -> ctx.getResponse()
			.send(name));
    }
}
