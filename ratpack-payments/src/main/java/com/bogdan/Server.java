package com.bogdan;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.vavr.Function1;
import io.vavr.control.Try;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.RequestLogger;
import ratpack.server.RatpackServer;
import ratpack.server.RatpackServerSpec;

import com.bogdan.employee.service.EmployeeService;

/**
 * @author Bogdan Marcut 18/01/2021.
 */
public class Server {

    private final EmployeeService employeeService;

    private final RatpackServer ratpackServer;

    public Server(final EmployeeService employeeService) {
	this.employeeService = employeeService;
	this.ratpackServer = Try.of(() -> createDefaultServer(this.defineApi()))
		.onFailure(this::handleError).get();
    }

    public void start() {
	Try.run(this.ratpackServer::start).onFailure(System.out::println);
    }

    public void stop() {
	Try.run(this.ratpackServer::stop);
    }

    private static RatpackServer createDefaultServer(final Action<Chain> handlers) {
	return createDefaultServer(makeApi(handlers), Server::configuration);
    }

    private static Action<Chain> makeApi(final Action<Chain> handlers) {
	return chain -> chain.prefix("api", handlers);
    }

    private static RatpackServer createDefaultServer(final Action<Chain> handlers,
	    final Function1<RatpackServerSpec, RatpackServerSpec> configuration) {
	try {
	    return RatpackServer.of(server -> configuration.apply(createEmptyServer(server))
		    .handlers(chain ->
			    handlers.execute(chain.all(RequestLogger.ncsa()))
		    )
	    );
	} catch (final Exception e) {
	    throw new IllegalStateException(e);
	}
    }

    private Action<Chain> defineApi() {
	return apiChain -> apiChain
		.insert(this.employeeService.buildServiceApi());
    }

    private static RatpackServerSpec createEmptyServer(final RatpackServerSpec initial)
	    throws Exception {
	return initial
		.registryOf(r -> r.add(JsonMapping.getJsonMapping()));
    }

    private static RatpackServerSpec configuration(final RatpackServerSpec server) {
	final Path currentRelativePath = Paths.get("").toAbsolutePath();
	try {
	    return server.serverConfig(
		    scb -> scb
			    .baseDir(currentRelativePath)
			    .publicAddress(new URI("http://0.0.0.0"))
			    .port(5050)
			    .threads(4)
	    );
	} catch (final Exception e) {
	    throw new IllegalStateException(e);
	}
    }

    private void handleError(final Throwable t) {
	System.err.println(t);
    }

}
