package com.bogdan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import com.bogdan.employee.model.EmployeeDto;

@MicronautTest
class MicronautPaymentsTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void should_get_an_employee_by_id() {
	final long employeeId = 1L;

	final EmployeeDto actualEmployee = this.client.toBlocking()
		.retrieve(HttpRequest.GET("/api/employee" + employeeId), EmployeeDto.class);

	assertTrue(this.application.isRunning());
	assertEquals(actualEmployee.getId(), employeeId);
    }

}
