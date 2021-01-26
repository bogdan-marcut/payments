package com.bogdan.employee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.exec.Promise;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MediaType;
import ratpack.http.Status;
import ratpack.http.internal.HttpHeaderConstants;

import com.bogdan.JsonMapping;
import com.bogdan.employee.model.EmployeeDto;
import com.bogdan.employee.repository.EmployeeRepository;
import com.google.inject.Inject;

/**
 * @author bogdan.marcut 24/01/2021.
 */
public class EmployeeChain implements Action<Chain> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeChain(final EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
    }

    @Override
    public void execute(final Chain chain) throws Exception {
	chain.prefix("employee", this.defineEmployeeApi());
    }

    private Action<? super Chain> defineEmployeeApi() {
	return chain -> chain
		.get(":id", this.getEmployee())
		.post("", this.addEmployee());
    }

    private Handler getEmployee() {
	return ctx -> Promise.value(ctx.getPathTokens().get("id"))
		.onError(this.buildErrorHttpResponse(ctx))
		.map(Long::valueOf)
		.map(this.employeeRepository::findById)
		.then(this.buildEmployeeHttpResponse(ctx));
    }

    private Handler addEmployee() {
	return ctx -> ctx.parse(EmployeeDto.class)
		//.onError(this.buildErrorHttpResponse(ctx))
		.map(this.employeeRepository::addEmployee)
		.then(this.buildAddEmployeeHttpResponse(ctx));
    }

    private Action<Throwable> buildErrorHttpResponse(final Context ctx) {
	return throwable -> {
	    this.logger.error("Could not process request", throwable);
	    ctx
		    .header(HttpHeaderConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON)
		    .getResponse()
		    .status(Status.BAD_REQUEST)
		    .send();
	};
    }

    private Action<EmployeeDto> buildEmployeeHttpResponse(final Context ctx) {
	return employeeDto -> ctx
		.header(HttpHeaderConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON)
		.getResponse()
		.send(JsonMapping.toJson(employeeDto));
    }

    private Action<Long> buildAddEmployeeHttpResponse(final Context ctx) {
	return employeeId -> ctx
		.header(HttpHeaderConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON)
		.getResponse()
		.send(JsonMapping.toJson(employeeId));
    }
}
