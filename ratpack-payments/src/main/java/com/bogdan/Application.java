package com.bogdan;

import ratpack.func.Action;
import ratpack.guice.BindingsSpec;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.handling.RequestLogger;
import ratpack.server.RatpackServer;
import ratpack.server.RatpackServerSpec;
import ratpack.server.ServerConfig;

import com.bogdan.employee.EmployeeModule;
import com.bogdan.employee.service.EmployeeChain;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class Application {

    private static final int PORT = 5050;

    public static void main(final String[] args) throws Exception {
	RatpackServer.start(initServer());
    }

    private static Action<RatpackServerSpec> initServer() {
	return server -> server
		.serverConfig(ServerConfig.embedded().port(PORT))
		.registryOf(registry -> registry.add(JsonMapping.getJsonMapping()))
		.registry(Guice.registry(buildDependencies()))
		.handlers(buildChain());
    }

    private static Action<BindingsSpec> buildDependencies() {
	return bindings -> bindings
		.module(EmployeeModule.class);
    }

    private static Action<Chain> buildChain() {
	return chain -> chain
		.all(RequestLogger.ncsa())
		.prefix("api", buildApi());
    }

    private static Action<Chain> buildApi() {
	return chain -> chain
		.insert(EmployeeChain.class);
    }

}
