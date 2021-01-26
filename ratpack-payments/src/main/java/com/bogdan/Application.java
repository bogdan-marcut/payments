package com.bogdan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.func.Action;
import ratpack.guice.BindingsSpec;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.handling.RequestLogger;
import ratpack.hikari.HikariModule;
import ratpack.server.RatpackServer;
import ratpack.server.RatpackServerSpec;
import ratpack.server.ServerConfig;
import ratpack.server.ServerConfigBuilder;

import com.bogdan.employee.EmployeeModule;
import com.bogdan.employee.service.EmployeeChain;
import com.zaxxer.hikari.HikariConfig;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static final int PORT = 5050;

    public static void main(final String[] args) throws Exception {
	RatpackServer.start(initServer());
    }

    private static Action<RatpackServerSpec> initServer() {
	return server -> server
		.serverConfig(generateServerConfig())
		.registryOf(registry -> registry.add(JsonMapping.getJsonMapping()))
		.registry(Guice.registry(buildDependencies()))
		.handlers(buildChain());
    }

    private static ServerConfigBuilder generateServerConfig() {
	return ServerConfig.embedded()
		.port(PORT);
    }

    private static Action<BindingsSpec> buildDependencies() {
	return bindings -> bindings
		.module(HikariModule.class, generateDatabaseConfig())
		.module(EmployeeModule.class);
    }

    private static Action<HikariConfig> generateDatabaseConfig() {
	return hikariConfig -> {
	    logger.info("DB URL {}", System.getenv("RATPACK_DB_URL"));
	    hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    hikariConfig.setJdbcUrl(System.getenv("RATPACK_DB_URL"));
	    hikariConfig.setUsername("root");
	    hikariConfig.setPassword("pass");
	};
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
